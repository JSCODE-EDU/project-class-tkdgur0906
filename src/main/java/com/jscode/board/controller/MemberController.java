package com.jscode.board.controller;

import com.jscode.board.dto.member.MemberResponse;
import com.jscode.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/info")
    public ResponseEntity<MemberResponse> Info(@RequestHeader("Authorization") String token) {
        System.out.println("token = " + token);
        MemberResponse response = memberService.findMemberInfo(token.substring(7));
        return ResponseEntity.ok(response);
    }
}
