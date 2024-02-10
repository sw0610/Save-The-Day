package com.savetheday.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoRes {
    private Long memberId;
    private String nickname;

    public static MemberInfoRes toMemberInfoRes(Long memberId, String nickname){
        return MemberInfoRes.builder()
                .memberId(memberId)
                .nickname(nickname)
                .build();
    }
}
