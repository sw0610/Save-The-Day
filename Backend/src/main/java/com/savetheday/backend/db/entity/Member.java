package com.savetheday.backend.db.entity;

import com.savetheday.backend.dto.request.SignUpMemberReq;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(length = 15, nullable = false)
    private String nickname;

    public static Member toMember(SignUpMemberReq req){
        return Member.builder()
                .email(req.getEmail())
                .password(req.getPassword())
                .nickname(req.getNickname())
                .build();
    }
}
