package com.springboot.springmongo.repository;

import com.springboot.springmongo.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task,String> {

    List<Task> findTaskBySeverity(int severity);

    @Query("{'assignee': ?0}")
    List<Task> getTasksByAssignee(String assignee);
}
