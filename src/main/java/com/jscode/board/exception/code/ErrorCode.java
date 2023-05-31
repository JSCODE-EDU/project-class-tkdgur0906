package com.jscode.board.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    NOT_FOUND_BOARD_EXCEPTION(404, "게시글이 존재하지 않습니다.", NOT_FOUND),
    INVALID_INPUT_EXCEPTION(400, "유효하지 않은 입력입니다.", BAD_REQUEST),
    DUPLICATE_EMAIL_EXCEPTION(409, "이미 사용중인 이메일입니다", CONFLICT),

    //member
    NOT_FOUND_MEMBER_EXCEPTION(404, "사용자가 존재하지 않습니다.", NOT_FOUND),

    //jwt
    NO_AUTHORITY_INFO_EXCEPTION(403, "권한 정보가 없는 토큰입니다.", FORBIDDEN),
    INVALID_JWT_SIGNATURE_EXCEPTION(401, "잘못된 JWT 서명입니다.", UNAUTHORIZED),
    EXPIRED_JWT_TOKEN_EXCEPTION(401, "만료된 JWT 토큰입니다.", UNAUTHORIZED),
    UNSUPPORTED_JWT_TOKEN_EXCEPTION(401,"지원되지 않는 JWT 토큰입니다.", UNAUTHORIZED),
    INVALID_JWT_TOKEN_EXCEPTION(401,"JWT 토큰이 잘못되었습니다.", UNAUTHORIZED),
    MISSING_TOKEN_EXCEPTION(404, "토큰이 요청에 포함되있지 않습니다.", NOT_FOUND);


    private final int status;
    private final String message;
    private final HttpStatus httpStatus;

}
