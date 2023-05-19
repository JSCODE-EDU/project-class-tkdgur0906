package com.jscode.board.service;

import com.jscode.board.domain.Member;
import com.jscode.board.dto.request.MemberFormRequest;
import com.jscode.board.exception.Member.DuplicateEmailException;
import com.jscode.board.exception.code.ErrorCode;
import com.jscode.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(MemberFormRequest request){
        if(isEmailDuplicate(request.getEmail())){
            throw new DuplicateEmailException(ErrorCode.DUPLICATE_EMAIL_EXCEPTION);
        }
        Member savedMember = memberRepository.save(MemberFormRequest.toEntity(request));
        return savedMember.getId();
    }

    private boolean isEmailDuplicate(String email){
        Optional<Member> findMember = memberRepository.findByEmail(email);
        return findMember.isPresent();
    }
}
