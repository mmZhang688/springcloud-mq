package cn.itcast.mq.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSimpleQueue() {
        String queueName = "simple.queue";
        String message="hello, spring amqp!";
        rabbitTemplate.convertAndSend(queueName,message);
    }

    @Test
    public void testSimpleQueue2() throws InterruptedException {
        String queueName = "simple.queue";
        String message="hello, spring amqp!";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queueName,message+i);
            Thread.sleep(20);
        }
    }
    @Test
    public void testfanoutExchange() throws InterruptedException {
        String exchangeName = "itcast.fanout";
        // 消息
        String message = "hello, everyone!";
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }
    @Test
    public void testdirctExchange() throws InterruptedException {
        String exchangeName = "itcast.direct";
        // 消息
        String message = "hello, !";
        String routingKey="red";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message+routingKey);
    }

    @Test
    public void testtopicExchange() throws InterruptedException {
        String exchangeName = "itcast.topic";
        // 消息
        String message = "喜报！孙悟空大战哥斯拉，胜!";
        String routingKey="china.weather";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message+routingKey);
    }
    @Test
    public void testSendMap() throws InterruptedException {
        // 准备消息
        Map<String,Object> msg = new HashMap<>();
        msg.put("name", "Jack");
        msg.put("age", 21);
        // 发送消息
        rabbitTemplate.convertAndSend("object.queue",msg);
    }
}
