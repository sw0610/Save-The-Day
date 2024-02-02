package com.savetheday.backend.db.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TaskId;

//    @Co
//    private Long userId;
    @ManyToOne
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    private Member member;

    @Column(nullable = false, length = 30)
    private String title;

    private String content;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime created_at;

    @Column(nullable = false)
    private LocalDate due_date;

    @Column(nullable = false, length = 10)
    private String status;

    @Column(nullable = false, length = 10)
    private String importance;

    @Column(length = 10)
    private String emotion;


}
