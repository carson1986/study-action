package jedis;

import com.google.common.collect.Maps;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * redis hash类型操作
 * hash特别适合用于存储对象
 * 每个hash，可以存储2^32-1 键值对(40多亿)
 */
public class HashDemo {

    public void operateHash(){
        try(Jedis jedis = JedisUtils.getJedis()){
            System.out.println("jedis.ping(): "+jedis.ping());

            jedis.del("config");

            //设置键值对
            jedis.hset("config", "ip", "127.0.0.1");

            //取得类型
            System.out.println("jedis.type(config): "+jedis.type("config"));

            Map<String, String> configField = Maps.newHashMap();
            configField.put("port", "8080");
            configField.put("maxalive", "3600");
            configField.put("weight", "1.0");

            //批量添加
            jedis.hmset("config", configField);

            //批量获取，全部属性
            System.out.println("jedis.hgetAll(config): "+jedis.hgetAll("config"));

            //获取获取，指定属性
            System.out.println(": "+jedis.hmget("config", "ip", "port"));

            //浮点数加
            jedis.hincrByFloat("config", "weight", 1.2);

            //获取单个属性
            System.out.println("jedis.hget(config): "+jedis.hget("config", "weight"));

            //获取所有的key
            System.out.println("jedis.hkeys(config): "+jedis.hkeys("config"));

            //获取所有的value
            System.out.println("jedis.hvals(config)： "+jedis.hvals("config"));

            //获取长度
            System.out.println("jedis.hlen(config): "+jedis.hlen("config"));

            //判断key是否存在
            System.out.println(": "+jedis.hexists("config", "weight"));

            //删除指定属性
            jedis.hdel("config", "weight");

            //判断key是否存在
            System.out.println(": "+jedis.hexists("config", "weight"));
        }
    }

    public static void main(String[] args) {
        HashDemo demo = new HashDemo();
        demo.operateHash();
    }
}
