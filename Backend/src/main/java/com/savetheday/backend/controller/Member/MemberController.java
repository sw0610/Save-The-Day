package com.savetheday.backend.controller.Member;

import com.savetheday.backend.dto.request.SignInMemberReq;
import com.savetheday.backend.dto.request.SignUpMemberReq;
import com.savetheday.backend.dto.response.MemberInfoRes;
import com.savetheday.backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/sign-in")
    public ResponseEntity<MemberInfoRes> getMember(@RequestBody SignInMemberReq signInMemberReq) {
        return ResponseEntity.ok(memberService.getMember(signInMemberReq));
    }

    @GetMapping("/email-check")
    public ResponseEntity<Boolean> getEmailExists(@RequestParam String email){
        return ResponseEntity.ok(memberService.getEmailExists(email));
    }


    @PostMapping("/sign-up")
    public ResponseEntity<MemberInfoRes> createMember(@RequestBody SignUpMemberReq signUpMemberReq) {
        return ResponseEntity.ok(memberService.createMember(signUpMemberReq));
    }
}
