package com.todo.todoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TodoProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoProjectApplication.class, args);
    }

}
