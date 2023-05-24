package com.jscode.board.dto.response;

import com.jscode.board.domain.BaseEntity;
import com.jscode.board.domain.Board;
import com.jscode.board.dto.request.BoardRequest;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.time.LocalDateTime;

@ApiModel(description = "생성 또는 조회 시 해당 게시글의 id, 제목, 내용, 생성날짜를 가진 response")
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
