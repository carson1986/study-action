package jedis;

import redis.clients.jedis.Jedis;

/**
 * redis Set类型操作
 * Set集合是String类型的无序集合
 * 集合成员是唯一的，集合中不能出现重复的数据
 * Set集合通过Hash表实现，添加、删除、查找时间复杂度都是O(1)
 */
public class SetDemo {

    public void operateSet(){
        try(Jedis jedis = JedisUtils.getJedis()){
            System.out.println("jedis.ping(): "+jedis.ping());

            jedis.del("set1");

            //取得类型
            System.out.println("jedis.type(set1): "+jedis.type("set1"));

            //添加元素
            jedis.sadd("set1", "user01", "user02", "user03");

            //取得类型
            System.out.println("jedis.type(set1): "+jedis.type("set1"));

            //遍历所有元素
            System.out.println("jedis.smembers(set1): "+jedis.smembers("set1"));

            //获取元素个数
            System.out.println("jedis.scard(set1): "+jedis.scard("set1"));

            //判断是否是集合元素
            System.out.println("jedis.sismember(set1): "+jedis.sismember("set1", "user04"));

            //删除元素
            jedis.srem("set1", "user02", "user01");

            //遍历所有元素
            System.out.println("jedis.smembers(set1): "+jedis.smembers("set1"));
        }
    }

    public static void main(String[] args) {
        SetDemo demo = new SetDemo();
        demo.operateSet();
    }
}
