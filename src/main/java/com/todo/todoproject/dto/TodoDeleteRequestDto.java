package com.todo.todoproject.dto;

import lombok.Getter;

@Getter
public class TodoDeleteRequestDto {
    private final String password;

    public TodoDeleteRequestDto(String password) {
        this.password = password;
    }

}