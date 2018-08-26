package com.thoughtworks.training.xueqing.todoservice.service;

import com.thoughtworks.training.xueqing.todoservice.dto.User;
import com.thoughtworks.training.xueqing.todoservice.model.Task;
import com.thoughtworks.training.xueqing.todoservice.model.Todo;
import com.thoughtworks.training.xueqing.todoservice.repository.TaskRepository;
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
    private final TaskRepository taskRepository;

    @Autowired
    private SpellCheckerService spellChecker;

    @Autowired
    public TodoService(TodoRepository todoRepository,TaskRepository taskRepository) {

        this.todoRepository = todoRepository;
        this.taskRepository = taskRepository;
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
        Integer id = getLoggedUserId();
        List<Todo> todos = todoRepository.findAllByUserIdEquals(id);
        spellChecker.check(todos);
//        spellChecker.check(todos, Todo::getName,Todo::setSuggestion);
        return todos;

    }

    public Todo save(Todo todo) {
        Integer id = getLoggedUserId();
        todo.setUserId(id);
        todo.setTime(new Date());
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

    public Task addTask(Task task) {
        taskRepository.save(task);
        System.out.println("add task "+task);
        return task;
    }
}
