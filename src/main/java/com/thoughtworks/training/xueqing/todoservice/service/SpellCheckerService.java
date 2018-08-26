package com.thoughtworks.training.xueqing.todoservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.thoughtworks.training.xueqing.todoservice.model.Todo;
import com.thoughtworks.training.xueqing.todoservice.security.SpellChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeoutException;
@Slf4j
@Component
public  class SpellCheckerService{
    @Autowired
    SpellChecker spellChecker;

//    @Retryable(maxAttempts=2, backoff = @Backoff(10))
    @HystrixCommand(fallbackMethod = "checkFallback")
    public List<Todo> check(List<Todo> todos){
        spellChecker.check(todos, Todo::getName,Todo::setSuggestion);
        return todos;
    }
//    @Recover
//    public List<Todo> failure(TimeoutException e, List<Todo> todos){
//        return todos;
//    }
    public List<Todo> checkFallback(List<Todo> todos){
        log.info("checkFallback");
        return todos;
    }
}
