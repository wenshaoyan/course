package com.wenshao.dal.group;

import com.wenshao.dal.constant.ZKConstant;
import com.wenshao.dal.watcher.ConnectionWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

import java.io.IOException;

/**
 * Created by wenshao on 2017/9/6
 */
public class JoinGroup extends ConnectionWatcher{
    public void join(String groupName,String memberName) throws KeeperException, InterruptedException{
        String path="/"+groupName+"/"+memberName;
        // 创建临时节点  session超时自动断开
        String createdPath=zk.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("Created:"+createdPath);
    }
    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        JoinGroup joinGroup = new JoinGroup();
        joinGroup.connect(ZKConstant.CONNECT_STRING);
        joinGroup.join("abc", "v1");
        //stay alive until process is killed or thread is interrupted
        Thread.sleep(Long.MAX_VALUE);
    }
}
