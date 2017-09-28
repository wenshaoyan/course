package com.wenshao.dal.handler;

import com.wenshao.dal.thriftgen.MessageService;
import com.wenshao.dal.thriftgen.UserService;
import org.apache.thrift.TException;

/**
 * Created by wenshao on 2017/9/5
 */
public class MessageHandler implements MessageService.Iface {

    public MessageHandler(){

    }
    public int isMessage() throws TException {
        System.out.println("aaaaaaaaa");
        return 1234;
    }
}
