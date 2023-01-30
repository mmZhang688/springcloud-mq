package cn.itcast.feign.clients;

import cn.itcast.feign.config.FeignConfig;
import cn.itcast.feign.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//如果是局部生效，则把它放到对应的@FeignClient这个注解中：
@FeignClient(value = "userserver",configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("/user/{id}")
    User findById(@PathVariable Long id);
}
