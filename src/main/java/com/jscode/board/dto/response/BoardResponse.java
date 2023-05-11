package com.jscode.board.dto.response;

import com.jscode.board.domain.BaseEntity;
import com.jscode.board.domain.Board;
import com.jscode.board.dto.request.BoardRequest;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BoardResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;



    public static BoardResponse from(Board board) {
        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .build();
    }
}
