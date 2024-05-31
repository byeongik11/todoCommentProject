package com.todo.todoproject.controller;

import com.todo.todoproject.dto.UpdateCommentRequest;
import com.todo.todoproject.entity.Comment;
import com.todo.todoproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<?> addComment(@RequestParam Long scheduleId,
                                        @RequestParam String content,
                                        @RequestParam String userId) {
        try {
            Comment comment = commentService.addComment(scheduleId, content, userId);
            System.out.println("댓글추가");
            return ResponseEntity.ok(comment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId,
                                           @RequestBody UpdateCommentRequest updateCommentRequest) {
        try {
            Comment updatedComment = commentService.updateComment(commentId, updateCommentRequest.getNewContent(), updateCommentRequest.getUserId());
            return ResponseEntity.ok(updatedComment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId,
                                           @RequestParam Long scheduleId,
                                           @RequestParam String userId) {
        if (commentId == null || scheduleId == null || userId == null) {
            return ResponseEntity.badRequest().body("commentId, scheduleId, userId가 생성되어야 합니다.");
        }

        try {
            commentService.deleteComment(commentId, scheduleId, userId);
            return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
