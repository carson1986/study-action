package jedis;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 单例redis操作工具类
 */
public class JedisUtils {

    private static int MAX_TOTAL = 50;

    private static int MAX_IDLE = 50;

    private static String HOST = "127.0.0.1";

    private static int PORT = 6379;

    private static int TIME_OUT = 10000;

    private static JedisPool jedisPool = null;

    static {
        buildPool();

        hotPool();
    }

    /**
     * 创建连接池
     */
    private static void buildPool(){
        if(jedisPool == null){
            Stopwatch stopwatch = Stopwatch.createStarted();
            System.out.println("创建连接池");
            JedisPoolConfig config = new JedisPoolConfig();
            //资源池中最大的连接数，默认值为8
            /**
             * 如何推算一个连接池的最大连接数maxTotal呢？
             * 实际上，这是一个很难精准回答的问题，主要是依赖的因素比较多。大致的推算方法是：业务QPS/单连接的QPS = 最大连接数。
             * 如何推算单个Jedis连接的QPS呢？
             * 假设一个Jedis命令操作的时间约为5ms（包含borrow +return + Jedis执行命令 + 网络延迟），
             * 那么，单个Jedis连接的QPS大约是100/5 =200。
             * 如果业务期望的QPS是100000，则需要的最大连接数为100000/200 =500。
             * 事实上，上面的估算仅仅是个理论值。
             * 在实际的生产场景中，还要预留一些资源，通常来讲所配置的maxTotal要比理论值大一些
             */
            config.setMaxTotal(MAX_TOTAL);
            //资源池允许最大空闲的连接数，默认值为8
            config.setMaxIdle(MAX_IDLE);
            //资源池确保最少空闲的连接数，默认值为0
            config.setMinIdle(0);
            //当资源池用尽后，调用者是否要等待，默认值为true
            config.setBlockWhenExhausted(true);
            //当资源池连接用尽后，调用者的最大等待时间（单位为毫秒）。默认值为-1
            config.setMaxWaitMillis(3000L);
            //向资源池借用连接时，是否做有效性检测（ping命令），如果是无效连接，会被移除，默认值为false，表示不做检测
            config.setTestOnBorrow(false);
            //向资源池归还连接时，是否做有效性检测（ping命令），如果是无效连接，会被移除，默认值为false
            config.setTestOnReturn(false);
            //如果为true，表示用一个专门的线程对空闲的连接进行有效性的检测扫描，如果有效性检测失败，即表示无效连接，会从资源池中移除。默认值为true，表示进行空闲连接的检测。这个选项存在一个附加条件，需要配置项timeBetweenEvictionRunsMillis的值大于0；否则，testWhileIdle不会生效
            config.setTestWhileIdle(true);
            //表示两次空闲连接扫描的活动之间，要睡眠的毫秒数，默认为30000毫秒，也就是30秒钟
            config.setTimeBetweenEvictionRunsMillis(30000L);
            //表示一个Jedis连接至少停留在空闲状态的最短时间，然后才能被空闲连接扫描线程进行有效性检测，默认值为60000毫秒，即60秒。也就是说在默认情况下，一条Jedis连接只有在空闲60秒后，才会参与空闲线程的有效性检测。这个选项存在一个附加条件，需要在timeBetweenEvictionRunsMillis大于0时才会生效。也就是说，如果不启动空闲检测线程，这个参数也没有什么意义
            config.setMinEvictableIdleTimeMillis(60000L);
            //表示空闲检测线程每次最多扫描的Jedis连接数，默认值为-1，表示扫描全部的空闲连接
            config.setNumTestsPerEvictionRun(-1);
            //是否开启jmx监控，默认值为true，建议开启
            config.setJmxEnabled(true);

            jedisPool = new JedisPool(config, HOST, PORT, TIME_OUT);

            System.out.println("创建连接池完成，耗时="+stopwatch.elapsed(TimeUnit.MILLISECONDS)+"毫秒");
        }
    }

    /**
     * 对连接池进行预热
     */
    private static void hotPool(){
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println("预热连接池");

        List<Jedis> jedisList = Lists.newArrayList();
        for(int i=0; i<MAX_TOTAL; i++){
            Jedis jedis = jedisPool.getResource();
            jedisList.add(jedis);
            jedis.ping();
        }

        for(Jedis jedis: jedisList){
            jedis.close();
        }

        System.out.println("预热连接池完成，耗时="+stopwatch.elapsed(TimeUnit.MILLISECONDS)+"毫秒");
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
