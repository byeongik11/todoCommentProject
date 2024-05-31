package com.todo.todoproject.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "todo_id", nullable = false)
	private Long todoId;

	@Setter
	private String title;

	@Setter
	private String content;

	@Setter
	private String userName;

	private String password;

	private LocalDateTime createdAt;

	@Builder
	public Todo(String title, String content, String userName, String password) {
		this.title = title;
		this.content = content;
		this.userName = userName;
		this.password = password;
		this.createdAt = LocalDateTime.now();
	}

}
