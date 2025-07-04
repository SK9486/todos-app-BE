package com.skCreations.todoApp.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateTaskRequest {
    @NotBlank(message = "Title cannot be blank")
    @Size(min = 3, max = 225, message = "Title must be between 3 and 225 characters")
    private String title;
    @NotNull(message = "Category ID cannot be null")
    @Min(value = 1, message = "Category ID must be at least 1")
    private Long categoryId;
}
