package com.thoughtworks.training.xueqing.todoservice.service;

import com.thoughtworks.training.xueqing.todoservice.dto.User;
import com.thoughtworks.training.xueqing.todoservice.model.Todo;
import com.thoughtworks.training.xueqing.todoservice.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    //    private final UserService userService;
    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
//        this.userService = userService;
    }


    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    private Integer getLoggedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getId();
    }

    public List<Todo> findAllByUser() {
        return todoRepository.findAllByUserIdEquals(getLoggedUserId());
    }

    public Todo save(Todo todo) {
        todo.setTime(new Date());
        System.out.println("----" + todo);
        return todoRepository.save(todo);
    }

    public Todo findById(Integer id){
        return todoRepository.findByIdEqualsAndUserIdEquals(id, getLoggedUserId());
    }

    public Todo update(Integer id, Todo newtodo) {
        Todo todo = todoRepository.findOne(id);
        todo.setName(newtodo.getName());
        todo.setCompleted(newtodo.getCompleted());
        todo.setDeleted(newtodo.getDeleted());
        todo.setTime(newtodo.getTime());
        todo.setTasks(newtodo.getTasks());
        return todoRepository.save(todo);
    }

    public void completed(Integer id) {
        Todo todo = todoRepository.findOne(id);
        todo.setCompleted(!todo.getCompleted());
        todoRepository.save(todo);
    }

    public void deleted(Integer id) {
        Todo todo = todoRepository.findOne(id);
        todo.setDeleted(!todo.getDeleted());
        todoRepository.save(todo);
    }
}
