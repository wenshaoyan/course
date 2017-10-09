package com.wenshao.dal.util;

import com.google.common.base.Objects;
import com.wenshao.dal.bean.CacheXmlBean;
import com.wenshao.dal.thriftgen.Banner;
import com.wenshao.dal.thriftgen.CacheService;
import com.wenshao.dal.thriftgen.User;
import com.wenshao.dal.thriftgen.UserService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.List;

/**
 * Created by wenshao on 2017/10/8.
 * thrift客户端
 */
public class CacheClientUtil {
    private static int port = 9090;
    private static String host = "localhost";
    private static TTransport transport;
    private static CacheService.Client client;
    private static CacheXmlBean cacheXmlBean;

    /**
     * 创建 TTransport
     *
     * @return socket
     */
    private static TTransport createTTransport() {
        return new TFramedTransport(new TSocket(host, port,2000));
    }

    /**
     * 开启 TTransport
     *
     * @param transport
     * @throws TTransportException
     */
    private static void openTTransport(TTransport transport) throws TTransportException {
        if (Objects.equal(transport, null)) {
            return;
        }
        transport.open();
    }

    /**
     * 关闭 TTransport
     *
     * @param transport
     */
    private static void closeTTransport(TTransport transport) {
        if (Objects.equal(transport, null)) {
            return;
        }
        transport.close();
    }

    private static TProtocol createTProtocol(TTransport transport) {
        if (Objects.equal(transport, null)) {
            return null;
        }
        TProtocol protocol = new TBinaryProtocol(transport);
        if (Objects.equal(protocol, null)) {
            return null;
        }
        return protocol;

    }

    public static TProtocol build1(String _host, int _port) {
        port = _port;
        host = _host;
        try {
            // 创建 TTransport
            transport = createTTransport();
            // 开启 TTransport
            openTTransport(transport);
            // 创建TProtocol
            return createTProtocol(transport);
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void build(String _host, int _port) {
        TTransport  m_transport = new TFramedTransport(new TSocket(_host, _port, 2000));
        TProtocol protocol = new TBinaryProtocol(m_transport);
        CacheService.Client client = new CacheService.Client(protocol);
        try {
            m_transport.open();
            setCacheClient(client);
        } catch (TException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public static void setCacheClient(CacheService.Client _client){
        client = _client;

    }
    public static CacheService.Client getCacheClient(){
        return client;
    }

    public static CacheXmlBean getCacheXmlBean() {
        return cacheXmlBean;
    }

    public static void setCacheXmlBean(CacheXmlBean cacheXmlBean) {
        CacheClientUtil.cacheXmlBean = cacheXmlBean;
    }
}

