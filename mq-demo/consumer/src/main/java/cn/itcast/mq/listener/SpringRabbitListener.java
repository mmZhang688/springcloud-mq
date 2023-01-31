package cn.itcast.mq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SpringRabbitListener {


    @RabbitListener(queues = "simple.queue")
    public void getMessage(String message){
        System.out.println("spring 消费者接收到消息：【" + message + "】");
    }
}
