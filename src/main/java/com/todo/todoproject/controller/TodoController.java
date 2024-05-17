package com.todo.todoproject.controller;

import com.todo.todoproject.dto.TodoDeleteRequestDto;
import com.todo.todoproject.dto.TodoUpdateRequestDto;
import com.todo.todoproject.entity.Todo;
import com.todo.todoproject.dto.TodoRequestDto;
import com.todo.todoproject.dto.TodoResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class TodoController {

    private final List<Todo> todos = new ArrayList<>();

    @PostMapping("/todos")
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto todoRequestDto) {
        Todo todo = new Todo(
                todoRequestDto.getTitle(),
                todoRequestDto.getContent(),
                todoRequestDto.getAssignee(),
                todoRequestDto.getPassword(),
                new Date()
        );
        todos.add(todo);
        return new TodoResponseDto(todos);
    }

   @GetMapping("/todos")
   public TodoResponseDto getTodos() {
        List<Todo> sortedTodos = new ArrayList<>(todos);
        sortedTodos.sort(Comparator.comparing(Todo::getCreatedDate).reversed());
        return new TodoResponseDto(sortedTodos);
   }

    @GetMapping("/todos/{id}")
    public TodoResponseDto getTodoById(@PathVariable int id) {
        Optional<Todo> todoOptional = todos.stream()
                                        .filter(todo -> todo.getId() == id)
                                        .findFirst();

        if(todoOptional.isPresent()) {
            List<Todo> result = new ArrayList<>();
            result.add(todoOptional.get());
            return new TodoResponseDto(result);
        } else {
            throw new IllegalArgumentException("id(" + id + ")를 찾을 수 없습니다.");
        }
    }

    @PutMapping("/todos/{id}")
    public TodoResponseDto updateTodo(@PathVariable int id, @RequestBody TodoUpdateRequestDto todoUpdateRequestDto) {
        Optional<Todo> todoOptional = todos.stream()
                .filter(todo -> todo.getId() == id)
                .findFirst();

        if (todoOptional.isPresent()) {
            Todo oldTodo = todoOptional.get();
            if (oldTodo.getPassword().equals(todoUpdateRequestDto.getPassword())) {
                Todo updatedTodo = new Todo(
                        todoUpdateRequestDto.getTitle(),
                        todoUpdateRequestDto.getContent(),
                        todoUpdateRequestDto.getAssignee(),
                        oldTodo.getPassword(),
                        oldTodo.getCreatedDate()
                );
                todos.remove(oldTodo);
                todos.add(updatedTodo);
                return new TodoResponseDto(Collections.singletonList(updatedTodo));
            } else {
                throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
            }
        } else {
            throw new IllegalArgumentException("id(" + id + ")를 찾을 수 없습니다.");
        }
    }

    @DeleteMapping("/todos/{id}")
    public TodoResponseDto deleteTodo(@PathVariable int id, @RequestBody TodoDeleteRequestDto todoDeleteRequestDto) {
        Optional<Todo> todoOptional = todos.stream()
                .filter(todo -> todo.getId() == id)
                .findFirst();

        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            if (todo.getPassword().equals(todoDeleteRequestDto.getPassword())) {
                todos.remove(todo);
                return new TodoResponseDto(Collections.singletonList(todo));
            } else {
                throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
            }
        } else {
            throw new IllegalArgumentException("id(" + id + ")를 찾을 수 없습니다.");
        }
    }
}
