package cn.itcast.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class SpringRabbitListener {


    /*@RabbitListener(queues = "simple.queue")
    public void getMessage(String message){
        System.out.println("spring 消费者接收到消息：【" + message + "】");
    }*/

    @RabbitListener(queues = "simple.queue")
    public void getMessage1(String message) throws InterruptedException {
        System.out.println("spring 消费者1接收到消息：【" + message + "】");
        Thread.sleep(20);
    }
    @RabbitListener(queues = "simple.queue")
    public void getMessage2(String message) throws InterruptedException {
        System.err.println("spring 消费者2接收到消息：【" + message + "】");
        Thread.sleep(200);
    }

    @RabbitListener(queues = "fanout.queue1")
    public void getfanoutqueue1Message(String message) throws InterruptedException {
        System.err.println("spring 消费者fanout.queue1接收到消息：【" + message + "】");
    }
    @RabbitListener(queues = "fanout.queue2")
    public void getfanoutqueue2Message(String message) throws InterruptedException {
        System.err.println("spring 消费者fanout.queue2接收到消息：【" + message + "】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "itcast.direct",type = ExchangeTypes.DIRECT),
            key = {"red","blue"}
    ))
    public void listenDirectQueue1(String msg){
        System.out.println("消费者接收到direct.queue1的消息：【" + msg + "】");
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "itcast.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "yellow"}
    ))
    public void listenDirectQueue2(String msg){
        System.out.println("消费者接收到direct.queue2的消息：【" + msg + "】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "itcast.topic",type = ExchangeTypes.TOPIC),
            key = {"china.#"}
    ))
    public void listentopicQueue1(String msg){
        System.out.println("消费者接收到topic.queue1的消息：【" + msg + "】");
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "itcast.topic",type = ExchangeTypes.TOPIC),
            key = {"#.news"}
    ))
    public void listentopicQueue2(String msg){
        System.out.println("消费者接收到topic.queue2的消息：【" + msg + "】");
    }
    @RabbitListener(queues = "object.queue")
    public void getobject(Map<String,Object> msg){
        System.out.println("spring 消费者1接收到消息：【" + msg + "】");
    }
}