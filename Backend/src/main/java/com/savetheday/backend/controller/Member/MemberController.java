package com.savetheday.backend.controller.Member;

import com.savetheday.backend.dto.request.SignInMemberReq;
import com.savetheday.backend.dto.response.DailyTaskListRes;
import com.savetheday.backend.dto.response.MemberInfoRes;
import com.savetheday.backend.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ResponseEntity<MemberInfoRes> createMember(@RequestBody SignInMemberReq signInMemberReq){
        return ResponseEntity.ok(memberService.createMember(signInMemberReq));

    }}
