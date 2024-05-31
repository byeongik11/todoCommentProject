package com.todo.todoproject.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateCommentRequest {
    private String newContent;
    private String userId;

}
