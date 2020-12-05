package rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 备胎交换机
 * 如果此时发送一条消息到nonnalExchange 上，当路由键等于"nonnalKey" 的
 * 时候，消息能正确路由到nonnalQueue这个队列中。如果路由键设为其他值，比如"errorKey",
 * 即消息不能被正确地路由到与nonnallixchange 绑定的任何队列上，此时就会发送给myAe ，进
 * 而发送到unroutedQueue 这个队列
 */
public class AEProducer {

    private static final String IP_ADDRESS = "127.0.0.1";
    private static final int PORT = 5672;//RabbitMQ 服务端默认端口号为5672

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername("root");
        factory.setPassword("root");

        Connection connection = factory.newConnection();//创建连接
        Channel channel = connection.createChannel();//创建信道

        Map<String, Object> argsMap = new HashMap<String , Object>();
        argsMap.put("alternate-exchange" , "myAe");
        channel.exchangeDeclare("normalExchange" , "direct" , true , false , argsMap);
        channel.exchangeDeclare("myAe" , "fanout" , true , false , null) ;
        channel.queueDeclare("normalQueue" , true , false , false , null);
        channel.queueBind("normalQueue" ,"normalExchange" , "normalKey");
        channel.queueDeclare("unroutedQueue" , true , false , false , null);
        channel.queueBind("unroutedQueue", "myAe", "");

        //发送一条持久化的消息: hello world !
        String message = "normalExchange!";
        channel.basicPublish("normalExchange", "normalKey1", true,
                MessageProperties.PERSISTENT_TEXT_PLAIN ,
                message.getBytes()) ;

    }
}
