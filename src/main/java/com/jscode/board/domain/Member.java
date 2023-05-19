package com.jscode.board.domain;

import com.jscode.board.dto.request.MemberFormRequest;
import lombok.AccessLevel;
import lombok.Builder;
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

    public static Member createMember(MemberFormRequest request){
        Member member = new Member();
        member.email = request.getEmail();
        member.password = request.getPassword();
        return member;
    }
}
