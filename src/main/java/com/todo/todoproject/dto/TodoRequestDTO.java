package com.todo.todoproject.dto;

import com.todo.todoproject.entity.Todo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoRequestDTO {

	private String title;

	private String content;

	private String userName;

	private String password;

	public Todo toEntity() {
		return Todo.builder()
			.title(title)
			.content(content)
			.userName(userName)
			.password(password)
			.build();
	}
}
