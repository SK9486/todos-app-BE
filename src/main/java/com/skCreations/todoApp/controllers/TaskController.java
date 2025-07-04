package com.skCreations.todoApp.controllers;

import com.skCreations.todoApp.dtos.CreateTaskRequest;
import com.skCreations.todoApp.dtos.TaskDto;
import com.skCreations.todoApp.dtos.UpdateTaskRequest;
import com.skCreations.todoApp.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(
        origins = "https://todos-app-fe.vercel.app",
        allowCredentials = "true"
)
@RestController
@RequestMapping("/api/tasks")

public class TaskController {

    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        TaskDto task = taskService.getTaskById(id);
        return ResponseEntity.ok(task); // 200 OK
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody CreateTaskRequest request) {
        TaskDto createdTask = taskService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id,
                                              @Valid @RequestBody UpdateTaskRequest request) {
        TaskDto updatedTask = taskService.updateTask(id, request);
        return ResponseEntity.ok(updatedTask); // 200 OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<TaskDto> toggleCompleted(@PathVariable Long id) {
        TaskDto toggledTask = taskService.toggleCompleted(id);
        return ResponseEntity.ok(toggledTask); // 200 OK
    }
}
