package com.wenshao.dal.server;

import com.wenshao.dal.constant.ZKConstant;
import com.wenshao.dal.util.IPUtil;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.log4j.Logger;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.*;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by wenshao on 2017/9/6
 */
public class Server {
    private static Logger logger = Logger.getLogger(String.valueOf(Server.class));
    private static final Pattern serverNamePattern = Pattern.compile(".*\\.(.*)\\$.*");

    private TProcessor processor;
    private int port;
    private static String getEnv(){
        HashSet<String> envSet = new HashSet<String>();
        envSet.add("develop");
        envSet.add("production");
        Map<String, String> map = System.getenv();
        String java_env = map.get("JAVA_ENV");
        if (java_env != null && envSet.contains(java_env))return java_env;
        return  "develop";
    }
    public Server(TProcessor processor, int port) {
        this.processor = processor;
        this.port = port;

    }

    /**
     * 根据服务和端口启动服务
     */
    public void startService() {
        TNonblockingServerSocket serverTransport = null;
        try {
            serverTransport = new TNonblockingServerSocket(port);
            TThreadedSelectorServer.Args serverParams = new TThreadedSelectorServer.Args(serverTransport);
            serverParams.processor(processor);
            serverParams.transportFactory(new TFramedTransport.Factory());
            serverParams.protocolFactory(new TBinaryProtocol.Factory());
            TServer server = new TThreadedSelectorServer(serverParams);
            String serverName = getServerName(processor.getClass().getName());
            logger.debug(serverName + " start service " + this.port);
            this.startZK(serverName);
            server.serve(); // 启动服务
        } catch (TTransportException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(2);
        }
    }

    private void startZK(String serverName) throws Exception {
        RetryNTimes retryNTimes = new RetryNTimes(10, 1000);
        String localIp = IPUtil.getLocalIp();
        String root = "dal/"+Server.getEnv()+"/" + serverName;
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(ZKConstant.CONNECT_STRING)
                .sessionTimeoutMs(ZKConstant.SESSION_TIMEOUT)
                .retryPolicy(retryNTimes)
                .namespace(root)
                .build();
        client.start();
        Stat stat = client.checkExists()
                .forPath("/"+localIp);
        if (stat != null) {
            throw new Exception(root + " Existing "+localIp+" Node");
        }
        List<ACL> acls = new ArrayList<ACL>();
        //添加第一个id，采用用户名密码形式
        Id id1 = new Id("digest",
                DigestAuthenticationProvider.generateDigest("admin:admin"));
        ACL acl1 = new ACL(ZooDefs.Perms.ALL, id1);
        acls.add(acl1);
        //添加第二个id，所有用户可读权限
        Id id2 = new Id("world", "anyone");
        ACL acl2 = new ACL(ZooDefs.Perms.READ, id2);
        acls.add(acl2);
        String nodeData = localIp + ":" + this.port;
        client.create()
                .withMode(CreateMode.EPHEMERAL)
                .forPath('/'+localIp,nodeData.getBytes());

    }

    // 找出server名称
    private String getServerName(String name) {
        Matcher m = serverNamePattern.matcher(name);
        String result = name;
        while (m.find()) {
            result = m.group(1);
        }
        return result;
    }


    /**
     * 启动服务器 TSimpleServer
     */
    private void startTSimpleServer() {
        try {
            TServerTransport serverTransport = new TServerSocket(port);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));
            // Use this for a multithreaded server
            // TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            System.out.println("Starting the simple server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * TMultiplexedProcessor启动服务器 TThreadedSelectorServer
     */
    private void startTThreadedSelectorServer() {
        try {
            TMultiplexedProcessor tMultiplexedProcessor = new TMultiplexedProcessor();
            //tMultiplexedProcessor.registerProcessor("UserService",userProcessor);
            //tMultiplexedProcessor.registerProcessor("MessageService",messageProcessor);

            // 传输通道 - 非阻塞方式
            TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(port);
            // 多线程半同步半异步
            TThreadedSelectorServer.Args serverParams = new TThreadedSelectorServer.Args(serverTransport);
            serverParams.processor(tMultiplexedProcessor);

            serverParams.transportFactory(new TFramedTransport.Factory());
            //二进制协议
            serverParams.protocolFactory(new TBinaryProtocol.Factory());
            // 多线程半同步半异步的服务模型
            TServer server = new TThreadedSelectorServer(serverParams);
            System.out.println("HelloTThreadedSelectorServer start....");
            server.serve(); // 启动服务


        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
