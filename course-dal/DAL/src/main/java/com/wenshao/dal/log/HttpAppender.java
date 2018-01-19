package com.wenshao.dal.log;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Created by wenshao on 2018/1/19.
 * 发送到http服务端
 */
public class HttpAppender extends AppenderSkeleton {

    @Override
    protected void append(LoggingEvent loggingEvent) {
        System.out.println("=============");
        //System.out.println(loggingEvent.getMessage());
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}
