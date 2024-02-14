package com.savetheday.backend.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ai_result")
public class AIResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

//    @Column(name="member_id")
//    private Long memberId;
//    @ManyToOne
//    @JoinColumn(name="member_id", insertable = false, updatable = false)
//    private Member member;

    private LocalDate taskDate;
    private String quote;
    private String imgUrl;
    private String imgTask;
    private String imgEmotion;
    private String fiction;

    public void updateImg(String url, String task, String emotion){
        this.imgEmotion = emotion;
        this.imgTask=task;
        this.imgUrl=url;
    }
    public void updateQuote(String quote){
        this.quote = quote;
    }
    public void updateFiction(String fiction){
        this.fiction = fiction;
    }


}
