package com.jscode.board.service;

import com.jscode.board.domain.Member;
import com.jscode.board.dto.member.MemberResponse;
import com.jscode.board.exception.member.NotFoundMemberException;
import com.jscode.board.jwt.TokenProvider;
import com.jscode.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    public MemberResponse findMemberInfo(String token){
        Authentication authentication = tokenProvider.getAuthentication(token);
        Long id = Long.valueOf(authentication.getName());
        Member member = memberRepository.findById(id).orElseThrow(NotFoundMemberException::new);
        return MemberResponse.from(member);
    }
}
