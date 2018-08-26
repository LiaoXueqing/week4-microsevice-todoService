package com.thoughtworks.training.xueqing.todoservice.repository;

import com.thoughtworks.training.xueqing.todoservice.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
