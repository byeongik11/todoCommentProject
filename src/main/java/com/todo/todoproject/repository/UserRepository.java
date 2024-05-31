package com.todo.todoproject.repository;

import com.todo.todoproject.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {
    User findByUserName(String userName);
}
