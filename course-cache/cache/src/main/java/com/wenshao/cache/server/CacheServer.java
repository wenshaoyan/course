package com.wenshao.cache.server;

import com.wenshao.cache.handler.CacheHandler;
import com.wenshao.cache.thriftgen.CacheService;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by wenshao on 2017/10/8.
 */

public class CacheServer{

    public static void main(String[] args) {
        CacheHandler handler = new CacheHandler();
        CacheService.Processor processor = new CacheService.Processor(handler);
        Server server = new Server(processor, 20001);
        server.startService();
    }
}