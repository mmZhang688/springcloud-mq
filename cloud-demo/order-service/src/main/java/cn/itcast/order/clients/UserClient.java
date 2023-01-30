package cn.itcast.order.clients;

import cn.itcast.order.config.FeignConfig;
import cn.itcast.order.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//如果是局部生效，则把它放到对应的@FeignClient这个注解中：
@FeignClient(value = "userserver",configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("/user/{id}")
    User findById(@PathVariable Long id);
}
