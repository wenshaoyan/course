package com.wenshao.dal.watcher;

import com.wenshao.dal.constant.ZKConstant;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
/**
 * Created by wenshao on 2017/9/6
 */
public class ConnectionWatcher implements Watcher {

    protected ZooKeeper zk;
    CountDownLatch connectedSignal=new CountDownLatch(1);

    public void connect(String host) throws IOException, InterruptedException{
        zk=new ZooKeeper(host, ZKConstant.SESSION_TIMEOUT, this);
        connectedSignal.await();
    }
    public void process(WatchedEvent event) {
        if(event.getState()==KeeperState.SyncConnected){
            connectedSignal.countDown();
        }
    }
    public void close() throws InterruptedException{
        zk.close();
    }

}
