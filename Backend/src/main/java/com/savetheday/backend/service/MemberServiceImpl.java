package com.savetheday.backend.service;

import com.savetheday.backend.db.entity.Member;
import com.savetheday.backend.db.repository.MemberRepository;
import com.savetheday.backend.dto.request.SignInMemberReq;
import com.savetheday.backend.dto.request.SignUpMemberReq;
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
    public MemberInfoRes getMember(SignInMemberReq signInMemberReq) {
        Member member = memberRepository.findByEmailAndPassword(signInMemberReq.getEmail(), signInMemberReq.getPassword())
                .orElse(null);

        if(member==null){
            return null;
        }
        else{
            return MemberInfoRes.toMemberInfoRes(member.getMemberId(), member.getNickname());
        }

    }

    @Override
    public Boolean getEmailExists(String email) {
        return memberRepository.existsByEmail(email);
    }


    @Override
    public MemberInfoRes createMember(SignUpMemberReq signUpMemberReq) {
         Member member = memberRepository.save(Member.toMember(signUpMemberReq));
         return MemberInfoRes.toMemberInfoRes(member.getMemberId(), member.getNickname());
    }



}
