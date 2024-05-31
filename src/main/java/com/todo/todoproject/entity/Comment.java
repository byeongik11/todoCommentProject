package com.todo.todoproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule scheduleId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Comment(String content, String userId, Schedule scheduleId, LocalDateTime createdAt) {
        this.content = content;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.createdAt = createdAt;
    }

    public Schedule getSchedule() {
        return scheduleId;
    }

}
