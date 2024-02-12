package com.savetheday.backend.service;

import com.savetheday.backend.dto.request.SignInMemberReq;
import com.savetheday.backend.dto.request.SignUpMemberReq;
import com.savetheday.backend.dto.response.MemberInfoRes;

public interface MemberService {
    MemberInfoRes createMember(SignUpMemberReq signUpMemberReq);

    MemberInfoRes getMember(SignInMemberReq signInMemberReq);

    Boolean getEmailExists(String email);
}
