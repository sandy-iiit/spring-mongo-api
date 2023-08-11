package com.springboot.springmongo.service;

import com.springboot.springmongo.model.Task;
import com.springboot.springmongo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository repository;

    @Autowired
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task addTask(Task task){
       task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
       return repository.save(task);
    }

    public List<Task> findAllTasks(){
        return repository.findAll();
    }

    public Task getTaskById(String taskId){
        return repository.findById(taskId).get();
    }

    public List<Task> getTaskBySeverity(int severity){
        return repository.findTaskBySeverity(severity);
    }

    public List<Task> getTaskByAssignee(String assignee){
        return repository.getTasksByAssignee(assignee);
    }

    public Task updateTask(Task taskRequest){
        Task existingTask=repository.findById(taskRequest.getTaskId()).get();
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setAssignee(taskRequest.getAssignee());
        existingTask.setSeverity(taskRequest.getSeverity());
        existingTask.setStoryPoint(taskRequest.getStoryPoint());
        return repository.save(existingTask);
    }

    public String deleteTask(String taskId){
         repository.deleteById(taskId);
        return "Task of id"+taskId+"is deleted";
    }
}
