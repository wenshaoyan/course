package com.wenshao.dal;

import com.google.common.base.Objects;
import com.wenshao.dal.thriftgen.*;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by wenshao on 2017/9/4
 */
public class client {
    private static int port = 9090;
    private static String ip = "localhost";
    private static CourseService.Client client;
    private static TTransport transport;
    /**
     * 创建 TTransport
     * @return
     */
    public static TTransport createTTransport(){
        TTransport transport = new TFramedTransport(new TSocket(ip, port));
        return transport;
    }
    /**
     * 开启 TTransport
     * @param transport
     * @throws TTransportException
     */
    public static void openTTransport(TTransport transport) throws TTransportException {
        if(Objects.equal(transport, null)){
            return;
        }
        transport.open();
    }
    /**
     * 关闭 TTransport
     * @param transport
     */
    public static void closeTTransport(TTransport transport){
        if(Objects.equal(transport, null)){
            return;
        }
        transport.close();
    }
    /**
     * 创建客户端
     * @return
     */
    public static CourseService.Client createClient(TTransport transport){
        if(Objects.equal(transport, null)){
            return null;
        }
        TProtocol protocol = new TBinaryProtocol(transport);
        if(Objects.equal(protocol, null)){
            return null;
        }
        CourseService.Client client = new CourseService.Client(protocol);
        return client;
    }
    public static void main(String[] args) {
        try {
            // 创建 TTransport
            transport = createTTransport();
            // 开启 TTransport
            openTTransport(transport);
            // 创建客户端
            client = createClient(transport);
            // 调用服务
            if(Objects.equal(client, null)){
                System.out.println("创建客户端失败...");
                return;
            }
            User user = new User();
            user.name ="wenshao";
            user.head ="head";
            user.tel ="1111";
            user.create_time = "2017-01-01";
            user.register_time = "2017-01-01";
            user.update_time = "2017-01-01";
            user.device_uuid = "1111";

            System.out.println(client.courseInsert(new Course()));
        } catch (RequestException e) {
            System.out.println("===================1");
            e.printStackTrace();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            System.out.println("===================2");
            e.printStackTrace();
        } finally {
            // 关闭 TTransport
            closeTTransport(transport);
        }
    }
}
