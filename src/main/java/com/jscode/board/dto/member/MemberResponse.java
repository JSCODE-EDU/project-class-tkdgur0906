package com.jscode.board.dto.member;

import com.jscode.board.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberResponse {

    private Long id;
    private String email;
    private LocalDateTime createdDate;

    public MemberResponse from(Member member){
        return new MemberResponse(member.getId(), member.getEmail(),member.getCreatedDate());
    }

}