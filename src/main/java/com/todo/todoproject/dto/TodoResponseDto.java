package com.todo.todoproject.dto;
import com.todo.todoproject.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class TodoResponseDto {
    private List<Todo> todos;
}
