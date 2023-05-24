package com.jscode.board.controller;

import com.jscode.board.dto.jwt.TokenDto;
import com.jscode.board.dto.jwt.TokenRequestDto;
import com.jscode.board.dto.member.MemberFormRequest;
import com.jscode.board.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/join")
    public ResponseEntity<Long> join(@Valid @RequestBody MemberFormRequest request){
        Long savedId = authService.join(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedId);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberFormRequest request){
        TokenDto tokenDto = authService.login(request);
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto request){
        TokenDto tokenDto = authService.reissue(request);
        return ResponseEntity.ok(tokenDto);
    }

}