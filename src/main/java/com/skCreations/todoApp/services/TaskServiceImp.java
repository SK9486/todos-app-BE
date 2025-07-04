package com.skCreations.todoApp.services;

import com.skCreations.todoApp.dtos.CreateTaskRequest;
import com.skCreations.todoApp.dtos.TaskDto;
import com.skCreations.todoApp.dtos.UpdateTaskRequest;
import com.skCreations.todoApp.entities.Category;
import com.skCreations.todoApp.entities.Task;
import com.skCreations.todoApp.exceptions.CategoryNotFoundException;
import com.skCreations.todoApp.exceptions.TaskNotFoundException;
import com.skCreations.todoApp.mappers.TaskMapper;
import com.skCreations.todoApp.repositories.CategoryRepository;
import com.skCreations.todoApp.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class TaskServiceImp implements TaskService {
    private CategoryRepository categoryRepository;
    private TaskRepository taskRepository;
    private TaskMapper taskMapper;

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(taskMapper::toDto).toList();
    }

    @Override
    public TaskDto createTask(CreateTaskRequest request) {
        Task task = taskMapper.toEntity(request);
        Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if (category == null) {
            throw new CategoryNotFoundException(request.getCategoryId());
        }
        task.setCategory(category);
        Task newTask = taskRepository.save(task);
        TaskDto taskDto = taskMapper.toDto(newTask);
        return taskDto;
    }

    @Override
    public TaskDto updateTask(Long id, UpdateTaskRequest request) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if (category == null) {
            throw new CategoryNotFoundException(request.getCategoryId());
        }
        taskMapper.updateTaskFromDto(request, task);
        task.setCategory(category);
        task = taskRepository.save(task);
        TaskDto taskDto = taskMapper.toDto(task);
        return taskDto;
    }

    @Override
    public void deleteTask(Long id) {
        var task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.delete(task);
    }

    @Override
    public TaskDto getTaskById(Long id) {
        var task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        return taskMapper.toDto(task);
    }

    @Override
    public TaskDto toggleCompleted(Long id) {
        var task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        task.setCompleted(!task.getCompleted());
        task = taskRepository.save(task);
        return taskMapper.toDto(task);
    }
}
