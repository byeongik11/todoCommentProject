package com.todo.todoproject.dto;

import lombok.Getter;

@Getter
public class TodoUpdateRequestDto {
    private final String title;
    private final String content;
    private final String assignee;
    private final String password;

    public TodoUpdateRequestDto(String title, String content, String assignee, String password) {
        this.title = title;
        this.content = content;
        this.assignee = assignee;
        this.password = password;
    }

}