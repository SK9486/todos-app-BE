package com.skCreations.todoApp.dtos;

import lombok.Data;

@Data
public class TaskDto {
    private Long id;
    private String title;
    private Boolean completed;
    private Long categoryId;
}
