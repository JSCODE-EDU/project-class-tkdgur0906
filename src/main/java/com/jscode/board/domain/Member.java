package com.jscode.board.domain;

import com.jscode.board.dto.member.MemberFormRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    public static Member createMember(MemberFormRequest request){
        Member member = new Member();
        member.email = request.getEmail();
        member.password = request.getPassword();
        member.authority = request.getAuthority();
        return member;
    }
}