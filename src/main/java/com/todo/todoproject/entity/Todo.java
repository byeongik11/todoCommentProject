package com.todo.todoproject.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    private static int idCounter = 0;

    private int id;
    private String title;
    private String content;
    private String assignee;
    private String password;
    private Date createdDate;

    public Todo(String title, String content, String assignee, String password, Date createdDate) {
        this.id = ++idCounter;
        this.title = title;
        this.content = content;
        this.assignee = assignee;
        this.password = password;
        this.createdDate = createdDate;
    }
}