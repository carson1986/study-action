package curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 利用curator操作zookeeper
 * 创建node节点，读取节点、同步更新节点数据、异步更新节点数据、删除节点
 */
public class ZookeeperExample {

    private Logger logger = LoggerFactory.getLogger(ZookeeperExample.class);

    //zookeeper连接地址
    private static final String CONNECTION_URL = "127.0.0.1:2181";

    /**
     * 创建node节点
     * @param nodePath 节点路径
     */
    public void createNode(String nodePath){
        logger.info("创建节点, nodePath={}", nodePath);
        //创建客户端
        CuratorFramework client = ZookeeperClientFactory.createSimple(CONNECTION_URL);

        try {
            //启动客户端，创建连接
            client.start();

            String data = "hello";
            byte[] paylod = data.getBytes("UTF-8");

            client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                    .forPath(nodePath, paylod);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CloseableUtils.closeQuietly(client);
        }
    }

    /**
     * 读取节点
     */
    public void readNode(){
        //创建客户端
        CuratorFramework client = ZookeeperClientFactory.createSimple(CONNECTION_URL);
        try{
            client.start();

            String zkPath = "/test/CRUD/node-1";

            Stat stat = client.checkExists().forPath(zkPath);
            if(stat != null){
                byte[] data = client.getData().forPath(zkPath);
                logger.info("zkPath={}, data={}", zkPath, new String(data, "UTF-8"));

                String parentPath = "/test";
                List<String> chilrens = client.getChildren().forPath(parentPath);
                for(String child : chilrens){
                    logger.info("child={}", child);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CloseableUtils.closeQuietly(client);
        }
    }

    /**
     * 更新节点数据
     * @param nodePath 节点路径
     */
    public void updateNode(String nodePath, String data){
        logger.info("更新节点数据, nodePath={}, data={}", nodePath, data);
        //创建客户端
        CuratorFramework client = ZookeeperClientFactory.createSimple(CONNECTION_URL);

        try {
            //启动客户端，创建连接
            client.start();

            byte[] paylod = data.getBytes("UTF-8");

            client.setData()
                    .forPath(nodePath, paylod);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CloseableUtils.closeQuietly(client);
        }
    }

    /**
     * 异步更新节点数据
     * @param nodePath 节点路径
     */
    public void updateNodeAsync(String nodePath, String data){
        logger.info("异步更新节点数据, nodePath={}, data={}", nodePath, data);
        //创建客户端
        CuratorFramework client = ZookeeperClientFactory.createSimple(CONNECTION_URL);

        try {
            //启动客户端，创建连接
            client.start();

            //书中的例子实际没有调用，不知道怎么回事；改为下面的回调才可以
            BackgroundCallback callback = new BackgroundCallback() {
                public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                    logger.info("callback processResult, eventType={}, code={}, path={}", curatorEvent.getType(),
                            curatorEvent.getResultCode(), curatorEvent.getPath());
                }
            };

            byte[] paylod = data.getBytes("UTF-8");

            client.setData()
                    .inBackground(callback)
                    .forPath(nodePath, paylod);

            Thread.sleep(10000); //休眠10s
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CloseableUtils.closeQuietly(client);
        }
    }

    /**
     * 删除节点
     * @param nodePath
     */
    public void deleteNode(String nodePath){
        logger.info("删除节点, nodePath={}", nodePath);
        //创建客户端
        CuratorFramework client = ZookeeperClientFactory.createSimple(CONNECTION_URL);

        try {
            //启动客户端，创建连接
            client.start();

            client.delete().forPath(nodePath);

            String parentPath = "/test";
            List<String> chilrens = client.getChildren().forPath(parentPath);
            for(String child : chilrens){
                logger.info("child={}", child);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            CloseableUtils.closeQuietly(client);
        }
    }


    public static void main(String[] args) {
        ZookeeperExample example = new ZookeeperExample();

        String nodePath = "/test/CRUD/node-1";
//        example.createNode(nodePath);

//        example.updateNode(nodePath, "hello world");
//        example.updateNodeAsync(nodePath, "hello every body,haha");
//        example.readNode();

        example.deleteNode("/test/CRUD/node-1");
    }
}
