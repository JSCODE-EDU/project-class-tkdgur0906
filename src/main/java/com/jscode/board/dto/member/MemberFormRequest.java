package com.jscode.board.dto.member;

import com.jscode.board.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberFormRequest {

    @Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$", message = "잘못된 이메일 형식입니다.")
    private String email;
    @Pattern(regexp = "^[^\\s]+$", message = "잘못된 비밀번호 형식입니다.")
    @Length(min = 8, max = 15, message = "비밀번호 자리는 8이상 15이하로 입력해주세요")
    private String password;

    public static Member toEntity(MemberFormRequest request) {
        return Member.createMember(request);
    }
}
