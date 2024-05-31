package com.todo.todoproject.controller;

import com.todo.todoproject.CommonResponse;
import com.todo.todoproject.dto.TodoRequestDTO;
import com.todo.todoproject.dto.TodoResponseDTO;
import com.todo.todoproject.entity.Todo;
import com.todo.todoproject.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/v1.0/todo")
@RestController
@AllArgsConstructor
public class TodoController {

	public final TodoService todoService;

	@PostMapping
	public ResponseEntity<CommonResponse<TodoResponseDTO>> postTodo(@RequestBody TodoRequestDTO dto) {
		Todo todo = todoService.createTodo(dto);
		TodoResponseDTO response = new TodoResponseDTO(todo);
		return ResponseEntity.ok()
			.body(CommonResponse.<TodoResponseDTO>builder()
				.statusCode(HttpStatus.OK.value())
				.msg("생성이 완료 되었습니다.")
				.data(response)
			.build());
	}

	@GetMapping("/{todoId}")
	public ResponseEntity<CommonResponse<TodoResponseDTO>> getTodo(@PathVariable Long todoId) {
		Todo todo = todoService.getTodo(todoId);
		TodoResponseDTO response = new TodoResponseDTO(todo);
		return ResponseEntity.ok()
			.body(CommonResponse.<TodoResponseDTO>builder()
				.statusCode(HttpStatus.OK.value())
				.msg("단건 조회가 완료 되었습니다.")
				.data(response)
				.build());
	}

	@GetMapping
	public ResponseEntity<CommonResponse<List<TodoResponseDTO>>> getTodos() {
		List<Todo> todos = todoService.getTodos();
		List<TodoResponseDTO> response = todos.stream()
			.map(TodoResponseDTO::new)
			.collect(Collectors.toList());
		return ResponseEntity.ok()
			.body(CommonResponse.<List<TodoResponseDTO>>builder()
				.statusCode(HttpStatus.OK.value())
				.msg("목록 조회이 완료 되었습니다.")
				.data(response)
				.build());
	}

	@PutMapping("/{todoId}")
	public ResponseEntity<CommonResponse<TodoResponseDTO>> putTodo(@PathVariable Long todoId, @RequestBody TodoRequestDTO dto) {
		Todo todo = todoService.updateTodo(todoId, dto);
		TodoResponseDTO response = new TodoResponseDTO(todo);
		return ResponseEntity.ok()
			.body(CommonResponse.<TodoResponseDTO>builder()
			.statusCode(HttpStatus.OK.value())
			.msg("수정이 완료 되었습니다.")
			.data(response)
			.build());
	}

	@DeleteMapping("/{todoId}")
	public ResponseEntity<CommonResponse> deleteTodo(@PathVariable Long todoId, @RequestBody TodoRequestDTO dto) {
		todoService.deleteTodo(todoId, dto.getPassword());
		return ResponseEntity.ok().body(CommonResponse.builder()
			.statusCode(HttpStatus.OK.value())
			.msg("삭제가 완료 되었습니다.")
			.build());
	}
}
