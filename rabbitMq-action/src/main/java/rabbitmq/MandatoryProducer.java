package rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 当mandatory 参数设为true时，交换器无法根据自身的类型和路由键找到一个符合条件
 * 的队列，那么RabbitMQ 会调用Basic.Return命令将消息返回给生产者。当mandatory参
 * 数设置为false 时，出现上述情形，则消息直接被丢弃
 */
public class MandatoryProducer {
    private static final String EXCHANGE_NAME = "exchange_demo";
    private static final String ROUTING_KEY = " routingkey_demo";
    private static final String QUEUE_NAME = "queue_demo";
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

        //发送一条持久化的消息: hello world !
        String message = "Hello World !";
        channel.basicPublish(EXCHANGE_NAME, "", true,
                MessageProperties.PERSISTENT_TEXT_PLAIN ,
                message.getBytes()) ;

        channel.addReturnListener(new ReturnListener() {
            public void handleReturn(int replyCode, String replyText,
                                     String exchange, String routingKey,
                                     AMQP.BasicProperties basicProperties,
                                     byte[] body) throws IOException {
                String message = new String(body);
                System.out.println("Basic.Return 返回的结果是: " + message);
            }
        });

//        channel.close(); //关闭信道，就不会输出了
//        connection.close();
    }
}
