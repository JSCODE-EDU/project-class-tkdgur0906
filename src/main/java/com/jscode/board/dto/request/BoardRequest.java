package com.jscode.board.dto.request;

import com.jscode.board.domain.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequest {

    @Length(min = 1, max = 15)
    @NotBlank
    private String title;
    @Length(min = 1, max = 1000)
    private String content;

    public static Board toEntity(BoardRequest boardRequest) {
        return Board.builder()
                .title(boardRequest.getTitle())
                .content(boardRequest.getContent()).build();
    }
}
