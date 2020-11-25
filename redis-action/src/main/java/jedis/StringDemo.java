package jedis;

import redis.clients.jedis.Jedis;

public class StringDemo {

    public void operateString(){
        try(Jedis jedis = JedisUtils.getJedis()){
            System.out.println("jedis.ping(): "+jedis.ping());

            //设置key0
            jedis.set("key0", "123456");

            //返回数据类型
            System.out.println("jedis.type(key0): "+jedis.type("key0"));

            //取得值
            System.out.println("jedis.get(key0): "+jedis.get("key0"));

            //判断key是否存在
            System.out.println("jedis.exists(key0): "+jedis.exists("key0"));

            //返回key对应value的长度
            System.out.println("jedis.strlen(key0): "+jedis.strlen("key0"));

            //截取字符串，0到-1，截取全部
            System.out.println(": "+jedis.substr("key0", 0, -1));

            //重命名
            jedis.rename("key0", "key0_new");

            //判断key是否存在
            System.out.println("jedis.exists(key0): "+jedis.exists("key0"));

            //批量插入
            jedis.mset("key1","val1", "key2", "val2", "key3", "100");

            //批量取值
            System.out.println("jedis.mget(key1, key2, key3): "+jedis.mget("key1", "key2", "key3"));

            //删除key1
            System.out.println("jedis.del(key1): "+jedis.del("key1"));

            //判断key是否存在
            System.out.println("jedis.exists(key1): "+jedis.exists("key1"));

            //取出旧值并设置新值
            System.out.println("jedis.getSet(key2): "+jedis.getSet("key2", "val3"));

            //自增1
            System.out.println("jedis.incr(key3): "+jedis.incr("key3"));

            //减10
            System.out.println("jedis.decrBy(key3, 10): "+jedis.decrBy("key3", 10));

            //加浮点型数值
            System.out.println("jedis.incrByFloat(key3): "+jedis.incrByFloat("key3", 1.1d));

            //返回0，只有key不存在时才设置
            System.out.println("jedis.setnx(key3): "+jedis.setnx("key3", "existValue"));

            //取得值
            System.out.println("jedis.get(key3): "+jedis.get("key3"));

            //返回0，只有key都不存在是才设置
            System.out.println("jedis.msetnx(key2, key3): "+jedis.msetnx("key2", "existVal1", "key3", "existVal2"));

            //设置key，2s失效
            jedis.setex("key4", 2, "2 seconds is no val");

            //判断key是否存在
            System.out.println("jedis.exists(key4): "+jedis.exists("key4"));

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //取得值
            System.out.println("jedis.get(key4): "+jedis.get("key4"));

            jedis.set("key6", "123456789");

            //从3开始，用新值覆盖旧值
            jedis.setrange("key6", 3, "abcdefg");

            System.out.println("jedis.get(key6): "+jedis.get("key6"));

            //返回所有匹配的key
            System.out.println("jedis.keys(key*): "+jedis.keys("key*"));
        }

    }

    public static void main(String[] args) {
        StringDemo demo = new StringDemo();
        demo.operateString();
    }
}
