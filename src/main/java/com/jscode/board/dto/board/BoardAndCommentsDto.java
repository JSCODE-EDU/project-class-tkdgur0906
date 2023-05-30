package com.jscode.board.dto.board;

import com.jscode.board.domain.Board;
import com.jscode.board.domain.Comment;
import com.jscode.board.dto.comment.CommentDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardAndCommentsDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private List<CommentDto> comments;

    @Builder
    public static BoardAndCommentsDto from(Board board, List<Comment> comments){
        BoardAndCommentsDto boardAndCommentsDto = new BoardAndCommentsDto();
        boardAndCommentsDto.id = board.getId();
        boardAndCommentsDto.title = board.getTitle();
        boardAndCommentsDto.content = board.getContent();
        boardAndCommentsDto.createdDate = board.getCreatedDate();

        List<CommentDto> commentDtos = comments.stream().map(CommentDto::from)
                .collect(Collectors.toList());

        boardAndCommentsDto.comments = commentDtos;
        return boardAndCommentsDto;
    }


}
