package curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * zookeeper client 工厂类
 */
public class ZookeeperClientFactory {

    /**
     * 利用newClient去创建
     * @param connectionUrl
     * @return
     */
    public static CuratorFramework createSimple(String connectionUrl){
        //重试策略，第一次重试等待1s，第二次重试等待2s，第三次重试等待4s
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000, 3);
        return CuratorFrameworkFactory.newClient(connectionUrl, retry);
    }

    /**
     * 利用builder方式创建
     * @param connectionUrl
     * @param retryPolicy
     * @param connectionTimeoutMs
     * @param sessionTimeoutMs
     * @return
     */
    public static CuratorFramework createWithOption(String connectionUrl, RetryPolicy retryPolicy, int connectionTimeoutMs, int sessionTimeoutMs){
        return CuratorFrameworkFactory.builder().connectString(connectionUrl)
                .retryPolicy(retryPolicy)
                .connectionTimeoutMs(connectionTimeoutMs)
                .sessionTimeoutMs(sessionTimeoutMs).build();
    }

    public static void main(String[] args) {
        String connectionUrl = "127.0.0.1:2181";
//        System.out.println(createSimple(connectionUrl));

        //重试策略，第一次重试等待1s，第二次重试等待2s，第三次重试等待4s
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000, 3);
        System.out.println(createWithOption(connectionUrl, retry, 2000, 2000));
    }
}
