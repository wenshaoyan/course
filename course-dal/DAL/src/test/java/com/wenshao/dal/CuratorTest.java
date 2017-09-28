package com.wenshao.dal;

import com.wenshao.dal.constant.ZKConstant;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.retry.RetryOneTime;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by UPC on 2017/9/6.
 */
public class CuratorTest {

    public static void main(String[] args) {
        String persistent_path = "/create_persistent";  // 持久节点
        String ephemeral_path = "/create_ephemeral";  // 临时节点
        String dataPath = "/data_path"; // 数据节点
        RetryNTimes retryNTimes = new RetryNTimes(10, 1000);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(ZKConstant.CONNECT_STRING)
                .sessionTimeoutMs(ZKConstant.SESSION_TIMEOUT)
                .retryPolicy(retryNTimes)
                .namespace("abc")
                .build();


        try {
            //  curator的监听事件
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            client.getConnectionStateListenable().addListener(new ConnectionStateListener(){

                public void stateChanged(CuratorFramework curatorFramework, ConnectionState newState) {
                    if (newState == ConnectionState.CONNECTED) {
                        try {
                            System.out.println("connected established");
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        countDownLatch.countDown(); //释放锁
                    } else {
                        System.out.println("connection state : " + newState.name());
                    }
                }
            });
            client.start();

            String path1 =  client.create()
                    .creatingParentContainersIfNeeded() //自动递归创建父节点
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(persistent_path);
            System.out.println("---------success to create node : " + path1);
            //创建持久化序列节点
            String path2 = client.create()
                    .creatingParentContainersIfNeeded() //自动递归创建父节点
                    .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                    .forPath(persistent_path);
            System.out.println("---------success to create node : " + path2);
            //创建临时节点
            String path3 = client.create()
                    .creatingParentContainersIfNeeded() //自动递归创建父节点
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath(ephemeral_path);
            System.out.println("---------success to create node : " + path3);

            //创建临时序列节点
            String path4 = client.create()
                    .creatingParentContainersIfNeeded() //自动递归创建父节点
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(ephemeral_path);
            System.out.println("---------success to create node : " + path4);
            //恢复现场
            client.delete()
                    .forPath(path1);
            client.delete()
                    .forPath(path2);
            client.delete()
                    .forPath(path3);
            client.delete()
                    .forPath(path4);
            /*client.delete()
                    .deletingChildrenIfNeeded()  //递归删除子节点
                    .forPath("/create_persistent");*/

            client.create()
                    .creatingParentContainersIfNeeded() //自动递归创建父节点
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(dataPath, "data".getBytes());

            //读取数据
            Stat stat_old = new Stat();
            byte[] bytes_old = client.getData()
                    .storingStatIn(stat_old) //同时读取节点stat
                    .forPath(dataPath);
            System.out.println("节点数据：" + new String(bytes_old));
            System.out.println("节点数据版本：" + stat_old.getVersion());
            //更新数据
            Stat stat_new = client.setData()
                    // .withVersion(0) //使用版本号
                    .forPath(dataPath, "new data".getBytes());//更新节点的数据
            System.out.println("更新数据后，节点数据版本：" + stat_new.getVersion());

            //读取数据
            byte[] bytes_new = client.getData()
                    .forPath(dataPath);
            System.out.println("更新数据后，节点数据：" + new String(bytes_new));

            client.delete()
                    .forPath(dataPath);



            String parentNode = "/parent_node";
            String childNode1 = parentNode + "/child_Node1";
            String childNode2 = parentNode + "/child_Node2";

            String path5 = client.create()
                    .creatingParentContainersIfNeeded() //自动递归创建父节点
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(childNode1);

            String path6 = client.create()
                    .creatingParentContainersIfNeeded() //自动递归创建父节点
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(childNode2);

            List<String> children = client.getChildren().forPath(parentNode);
            for (String child : children) {
                /**
                 * 输出：
                 * 节点：child_Node1
                 * 节点：child_Node2
                 */
                System.out.println("节点：" + child);
            }

            client.delete().deletingChildrenIfNeeded().forPath(parentNode);


            String node_to_listen = "/listened_node";

            client.create()
                    .creatingParentContainersIfNeeded() //自动递归创建父节点
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(node_to_listen,"old data".getBytes());

            final NodeCache nodeCache = new NodeCache(client, node_to_listen, false);
            nodeCache.getListenable().addListener(new NodeCacheListener() {
                public void nodeChanged() throws Exception {
                    System.out.println("Node data is changed, new data: " +
                            new String(nodeCache.getCurrentData().getData()));
                }
            });
            nodeCache.start();

            Thread.sleep(1000);

            client.setData()
                    .forPath(node_to_listen, "new data".getBytes());//更新节点的数据

            Thread.sleep(1000);

            nodeCache.close();

            client.delete().deletingChildrenIfNeeded().forPath(node_to_listen);

            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
