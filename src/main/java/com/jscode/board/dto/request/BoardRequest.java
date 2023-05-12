package com.jscode.board.dto.request;

import com.jscode.board.domain.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequest {

    private String title;
    private String content;

    public static Board toEntity(BoardRequest boardRequest) {
        return Board.builder()
                .title(boardRequest.getTitle())
                .content(boardRequest.getContent()).build();
    }
}
