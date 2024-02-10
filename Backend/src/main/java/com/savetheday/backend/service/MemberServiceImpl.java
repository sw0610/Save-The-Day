package com.savetheday.backend.service;

import com.savetheday.backend.db.entity.Member;
import com.savetheday.backend.db.repository.MemberRepository;
import com.savetheday.backend.dto.request.SignInMemberReq;
import com.savetheday.backend.dto.response.MemberInfoRes;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public MemberInfoRes createMember(SignInMemberReq signInMemberReq) {
         Member member = memberRepository.save(Member.toMember(signInMemberReq));
         return MemberInfoRes.toMemberInfoRes(member.getMemberId(), member.getNickname());
    }


}
