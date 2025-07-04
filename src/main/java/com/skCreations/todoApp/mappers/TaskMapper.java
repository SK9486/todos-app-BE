package com.skCreations.todoApp.mappers;

import com.skCreations.todoApp.dtos.CreateTaskRequest;
import com.skCreations.todoApp.dtos.TaskDto;
import com.skCreations.todoApp.dtos.UpdateTaskRequest;
import com.skCreations.todoApp.entities.Category;
import com.skCreations.todoApp.entities.Task;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    // Convert Entity -> DTO
    @Mapping(source = "category.id", target = "categoryId")
    TaskDto toDto(Task task);

    // Convert CreateRequest -> Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "completed", constant = "false") // default false
    @Mapping(target = "category", ignore = true)       // set manually in service
    Task toEntity(CreateTaskRequest request);

    // Update Entity from UpdateRequest
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "completed", ignore = true)      // don't allow completed status to be changed here
    @Mapping(target = "category", ignore = true)       // manually update in service
    void updateTaskFromDto(UpdateTaskRequest request, @MappingTarget Task task);
}
