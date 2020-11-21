package jedis;

import redis.clients.jedis.Jedis;

public class StringDemo {

    public void operateString(){
        Jedis jedis = new Jedis("localhost", 6379);
        System.out.println("jedis.ping(): "+jedis.ping());
        jedis.close();
    }

    public static void main(String[] args) {
        StringDemo demo = new StringDemo();
        demo.operateString();
    }
}
