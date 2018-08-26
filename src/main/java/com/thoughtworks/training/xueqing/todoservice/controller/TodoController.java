package com.thoughtworks.training.xueqing.todoservice.controller;

import com.thoughtworks.training.xueqing.todoservice.model.Task;
import com.thoughtworks.training.xueqing.todoservice.model.Todo;
import com.thoughtworks.training.xueqing.todoservice.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> getTodos() {
        List<Todo> todoList = todoService.findAllByUser();
        return todoList;
    }

    @GetMapping("/{id}")
    public Todo getOneById(@PathVariable Integer id) {
        return todoService.findById(id);
    }

    @PostMapping
    public Todo save(@RequestBody Todo todo) {
        return todoService.save(todo);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody Todo todo) {
        todoService.update(id, todo);
    }
    @PutMapping("/addTask")
    public Task update_addTask(@RequestBody Task task) {
        return todoService.addTask(task);
    }

    @PutMapping("/completed/{id}")
    public void completed(@PathVariable Integer id) {
        todoService.completed(id);
    }

    @DeleteMapping("/{id}")
    public void deleted(@PathVariable Integer id) {
        todoService.deleted(id);
    }

}
