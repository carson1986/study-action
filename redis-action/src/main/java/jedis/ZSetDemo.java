package jedis;

import com.google.common.collect.Maps;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Map;
import java.util.Set;

/**
 * redis ZSet类型操作
 * ZSet有序集合和Set集合都是String类型元素的集合，且不存在重复的元素
 * 不同的是Zset的每个元素都是关联一个double类型的分数，用于从小到大排序
 */
public class ZSetDemo {

    public void operateZSet(){
        try(Jedis jedis = JedisUtils.getJedis()){
            System.out.println("jedis.ping(): "+jedis.ping());

            jedis.del("salary");

            Map<String, Double> members = Maps.newHashMap();
            members.put("u01", 1000.0d);
            members.put("u02", 3000.0d);
            members.put("u03", 3000.0d);
            members.put("u04", 13000.0d);
            members.put("u05", 23000.0d);

            //批量新增元素
            jedis.zadd("salary", members);

            //取得类型
            System.out.println("jedis.type(salary): "+jedis.type("salary"));

            //获取集合元素数量
            System.out.println("jedis.zcard(salary): "+jedis.zcard("salary"));

            //按下标获取元素，[0,-1]全部元素
            System.out.println("jedis.zrange(salary): "+jedis.zrange("salary", 0, -1));

            //倒序，按下标获取元素，[0,-1]全部元素
            System.out.println("jedis.zrevrange(salary): "+jedis.zrevrange("salary", 0, -1));

            //按分数，获取元素
            System.out.println("jedis.zrangeByScore(salary): "+jedis.zrangeByScore("salary", 1000.0, 10000.0));

            //带按分数，获取元素，返回分数
            Set<Tuple> tuples = jedis.zrangeByScoreWithScores("salary", 1000, 10000);
            for(Tuple tuple : tuples){
                System.out.println("tuple.get(): "+tuple.getElement()+", score: "+tuple.getScore());
            }

            //获取分数范围内的元素数量
            System.out.println("jedis.zcount(): "+jedis.zcount("salary", 1000, 10000));

            //获取指定元素的分数
            System.out.println("jedis.zscore(salary, u01): "+jedis.zscore("salary", "u01"));

            //获取指定元素的下标
            System.out.println("jedis.zrank(salary, u01): "+jedis.zrank("salary", "u01"));

            //倒序获取指定元素的下标
            System.out.println("jedis.zrevrank(salary, u01): "+jedis.zrevrank("salary", "u01"));

            //删除元素
            System.out.println("jedis.zrem(salary, u01, u02): "+jedis.zrem("salary", "u01", "u02"));

            //按下标删除元素
            System.out.println("jedis.zremrangeByRank(salary, 0, 1): "+jedis.zremrangeByRank("salary", 0, 1));

            //按分数删除元素
            System.out.println(": "+jedis.zremrangeByScore("salary", 20000, 30000));

            //按下标获取元素，[0,-1]全部元素
            System.out.println("jedis.zrange(salary): "+jedis.zrange("salary", 0, -1));

            Map<String, Double> members2 = Maps.newHashMap();
            members2.put("u11", 1111.0);
            members2.put("u12", 2222.0);
            members2.put("u13", 3333.0);

            jedis.zadd("salary", members2);

            //增加指定分数
            System.out.println("jedis.zincrby(salary, 10000, u13): "+jedis.zincrby("salary", 10000, "u13"));

            //按下标获取元素，[0,-1]全部元素
            System.out.println("jedis.zrange(salary): "+jedis.zrange("salary", 0, -1));

        }
    }

    public static void main(String[] args) {
        ZSetDemo demo = new ZSetDemo();
        demo.operateZSet();
    }
}
