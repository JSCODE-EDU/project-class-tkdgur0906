package com.jscode.board.controller;

import com.jscode.board.dto.member.MemberFormRequest;
import com.jscode.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<Long> memberJoin(@Valid @RequestBody MemberFormRequest request){
        Long savedId = memberService.join(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedId);
    }

}
