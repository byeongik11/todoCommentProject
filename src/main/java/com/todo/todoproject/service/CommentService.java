package com.todo.todoproject.service;

import com.todo.todoproject.entity.Comment;
import com.todo.todoproject.entity.Schedule;
import com.todo.todoproject.repository.CommentRepository;
import com.todo.todoproject.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public Comment addComment(Long scheduleId, String content, String userId) {
        if (scheduleId == null) {
            throw new IllegalArgumentException("Schedule ID는 null이 아니여야 됩니다");
        }

        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("내용은 비어있지 않아야 됩니다.");
        }

        Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
        if (!scheduleOptional.isPresent()) {
            throw new IllegalArgumentException("Schedule을 찾을 수 없습니다.");
        }

        Schedule schedule = scheduleOptional.get();
        Comment comment = new Comment(content, userId, schedule, LocalDateTime.now());

        return commentRepository.save(comment);
    }

    @Transactional
    public Comment updateComment(Long commentId, String newContent, String userId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isEmpty()) {
            throw new IllegalArgumentException("댓글을 찾을 수 없습니다.");
        }

        Comment comment = commentOptional.get();
        Schedule schedule = comment.getSchedule();
        if (schedule == null) {
            throw new IllegalArgumentException("스케쥴을 찾을 수 없습니다.");
        }

        if (!comment.getUserId().equals(userId)) {
            throw new IllegalArgumentException("이 댓글을 업데이트할 권한이 없습니다.");
        }

        comment.setContent(newContent);
        comment.setCreatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    public void deleteComment(Long commentId, Long scheduleId, String userId) {
        if (commentId == null || scheduleId == null || userId == null) {
            throw new IllegalArgumentException("commentId, scheduleId, userId가 null이면 안됩니다.");
        }

        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isEmpty()) {
            throw new IllegalArgumentException("댓글을 찾을 수 없습니다.");
        }

        Comment comment = commentOptional.get();
        Schedule schedule = comment.getSchedule();
        if (schedule == null || !schedule.getId().equals(scheduleId)) {
            throw new IllegalArgumentException("스케쥴을 찾을 수 없거나 맞지 않습니다.");
        }

        if (!comment.getUserId().equals(userId)) {
            throw new IllegalArgumentException("You are not authorized to delete this comment");
        }

        commentRepository.deleteById(commentId);
    }
}