package com.savetheday.backend.service;

import com.savetheday.backend.dto.request.SignInMemberReq;
import com.savetheday.backend.dto.response.MemberInfoRes;

public interface MemberService {
    MemberInfoRes createMember(SignInMemberReq signInMemberReq);
}
