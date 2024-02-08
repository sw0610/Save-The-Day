package com.savetheday.backend.db.entity;

import com.savetheday.backend.dto.request.DailyTaskReq;
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
    private Long taskId;

    @Column(name="member_id")
    private Long memberId;
    @ManyToOne
    @JoinColumn(name="member_id", insertable = false, updatable = false)
    private Member member;

    @Column(nullable = false, length = 30)
    private String title;

    private String content;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false, length = 15)
    private String processStatus;

    @Column(nullable = false, length = 15)
    private String importance;

    @Column(length = 15)
    private String emotion;

    public static Task toTask(Long memberId, DailyTaskReq req){
        return Task.builder()
                .memberId(memberId)
                .title(req.getTitle())
                .content(req.getContent())
                .dueDate(req.getDueDate())
                .processStatus(req.getProcessStatus())
                .importance(req.getImportance())
                .build();
    }

    public void updateTask(DailyTaskReq req){
        this.title = req.getTitle();
        this.content = req.getContent();
        this.dueDate = req.getDueDate();
        this.processStatus = req.getProcessStatus();
        this.importance = req.getImportance();
        this.emotion = req.getEmotion();
    }


}
