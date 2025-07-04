package com.skCreations.todoApp.services;

import com.skCreations.todoApp.dtos.CreateTaskRequest;
import com.skCreations.todoApp.dtos.TaskDto;
import com.skCreations.todoApp.dtos.UpdateTaskRequest;

import java.util.List;

public interface TaskService {
    public List<TaskDto> getAllTasks();
    public TaskDto createTask(CreateTaskRequest request);
    public TaskDto updateTask(Long id,UpdateTaskRequest request);
    public void deleteTask(Long id);
    public TaskDto getTaskById(Long id);
    public TaskDto toggleCompleted(Long id);
}
