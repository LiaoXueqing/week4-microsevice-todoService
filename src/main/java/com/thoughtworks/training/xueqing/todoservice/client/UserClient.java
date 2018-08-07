package com.thoughtworks.training.xueqing.todoservice.client;

import com.thoughtworks.training.xueqing.todoservice.dto.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
@FeignClient(name="user",url="http://localhost:8081/users")
public interface UserClient {
    @PostMapping("/verifications")
    User verifyToken(String token);
}
