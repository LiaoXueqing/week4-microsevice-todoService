package com.thoughtworks.training.xueqing.todoservice.controller;

import com.thoughtworks.training.xueqing.todoservice.model.User;
import com.thoughtworks.training.xueqing.todoservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping
    public String login(@RequestBody User user){
        boolean verify = userService.verify(user.getName(), user.getPassword());
        if(verify){
            //生成token
            String token = userService.generateToken(user.getName());
            return token;
        }
        return null;
    }

}
