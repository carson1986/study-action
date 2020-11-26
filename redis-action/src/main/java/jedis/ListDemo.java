package jedis;

import redis.clients.jedis.Jedis;

/**
 * redis list类型操作
 */
public class ListDemo {

    public void operateList(){
        try(Jedis jedis = JedisUtils.getJedis()){
            System.out.println("jedis.ping(): "+jedis.ping());

            jedis.del("list1");

            //从list尾部添加三个元素
            jedis.rpush("list1", "zhangsan", "lisi", "wangwu");

            //取得类型
            System.out.println("jedis.type(list1): "+jedis.type("list1"));

            //遍历区间0, -1, 全部元素
            System.out.println("jedis.lrange(list1): "+jedis.lrange("list1", 0, -1));

            //遍历区间1, 2, 获取区间元素
            System.out.println("jedis.lrange(list1): "+jedis.lrange("list1", 1, 2));

            //获取list1列表长度
            System.out.println("jedis.llen(list1): "+jedis.llen("list1"));

            //获取下标为1的元素
            System.out.println("jedis.lindex(list1): "+jedis.lindex("list1", 1));

            //左侧弹出元素
            System.out.println("jedis.lpop(list1): "+jedis.lpop("list1"));

            //右侧弹出元素
            System.out.println("jedis.rpop(list1): "+jedis.rpop("list1"));

            //遍历区间0, -1, 全部元素
            System.out.println("jedis.lrange(list1): "+jedis.lrange("list1", 0, -1));

            //设置下标为0的元素val
            jedis.lset("list1", 0, "lisi2");

            //遍历区间0, -1, 全部元素
            System.out.println("jedis.lrange(list1): "+jedis.lrange("list1", 0, -1));
        }
    }

    public static void main(String[] args) {
        ListDemo demo = new ListDemo();
        demo.operateList();
    }
}
