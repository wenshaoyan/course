package com.wenshao.dal.group;
import java.io.IOException;
import java.util.List;

import com.wenshao.dal.constant.ZKConstant;
import com.wenshao.dal.watcher.ConnectionWatcher;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
/**
 * Created by wenshao on 2017/9/6
 */

public class ListGroup extends ConnectionWatcher {
    public void list(String groupNmae) throws KeeperException, InterruptedException{
        String path ="/"+groupNmae;
        try {
            List<String> children = zk.getChildren(path, false);
            if(children.isEmpty()){
                System.out.printf("No memebers in group %s\n",groupNmae);
                System.exit(1);
            }
            for(String child:children){
                System.out.println(child);
            }
        } catch (KeeperException.NoNodeException e) {
            System.out.printf("Group %s does not exist \n", groupNmae);
            System.exit(1);
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ListGroup listGroup = new ListGroup();
        listGroup.connect(ZKConstant.CONNECT_STRING);
        listGroup.list("abc");
        listGroup.close();
    }
}