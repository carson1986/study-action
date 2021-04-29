package curator;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 利用zookeeper生成分布式ID
 * 用客户端去查看，临时顺序节点的父节点是持久节点，因为临时节点不能创建子节点
 * 再次执行main方法是，id还是在前次执行的基础上递增，因为这个id会在父节点上保存
 */
public class ZooKeeperIDGenerator {
    Logger logger = LoggerFactory.getLogger(ZooKeeperIDGenerator.class);

    private static final String CONNECTION_URL = "127.0.0.1:2181";

    private static final String PATH_PREFIX = "/test/IDGenerator/id-";

    private CuratorFramework client = null;

    public void init(){
        client = ZookeeperClientFactory.createSimple(CONNECTION_URL);
        client.start();
    }

    public void destory(){
        if(client != null){
            CloseableUtils.closeQuietly(client);
        }
    }

    private String createSeqNode(String pathPrefix){
        try {
            String nodePath = client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(pathPrefix, null);
            return nodePath;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String makeId(String pathPrefix){
        if(StringUtils.isBlank(pathPrefix)){
            return null;
        }
        String nodeSeq = createSeqNode(pathPrefix);
        if(nodeSeq == null){
            return null;
        }

        return StringUtils.substringAfter(nodeSeq, PATH_PREFIX);
    }


    public static void main(String[] args) {
        ZooKeeperIDGenerator idGenerator = new ZooKeeperIDGenerator();
        idGenerator.init();
        for(int i=1; i<=20; i++){
            String id = idGenerator.makeId(PATH_PREFIX);
            System.out.println("id: "+id);
        }
        idGenerator.destory();
    }
}
