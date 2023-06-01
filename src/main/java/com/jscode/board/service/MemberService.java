package com.jscode.board.service;

import com.jscode.board.domain.Member;
import com.jscode.board.dto.member.MemberResponse;
import com.jscode.board.exception.member.NotFoundMemberException;
import com.jscode.board.jwt.TokenProvider;
import com.jscode.board.repository.MemberRepository;
import com.jscode.board.util.SecurityUtil;
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

    public MemberResponse findMemberInfo(){
        Long currentMemberId = SecurityUtil.getCurrentMemberId();
        Member member = memberRepository.findById(currentMemberId).orElseThrow(NotFoundMemberException::new);
        return MemberResponse.from(member);
    }
}
