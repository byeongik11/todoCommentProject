package com.todo.todoproject.dto;

import lombok.Data;

@Data
public class TodoRequestDto {
    private String title;
    private String content;
    private String assignee;
    private String password;
}
