package cn.itcast.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("itcast.fanout");
    }

    @Bean
    public Queue fanoutqueue1(){
        return new Queue("fanout.queue1");
    }
    /**
     * 绑定队列和交换机
     */
    @Bean
    public Binding fanoutBinding(FanoutExchange fanoutExchange,Queue fanoutqueue1){
        return BindingBuilder.bind(fanoutqueue1).to(fanoutExchange);
    }
    /**
     * 第2个队列
     */
    @Bean
    public Queue fanoutQueue2(){
        return new Queue("fanout.queue2");
    }
    /**
     * 绑定队列和交换机
     */
    @Bean
    public Binding fanoutBinding2(FanoutExchange fanoutExchange,Queue fanoutQueue2){
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }
    @Bean
    public Queue fanoutQueue3(){
        return new Queue("object.queue");
    }
}
