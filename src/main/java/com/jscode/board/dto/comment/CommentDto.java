package com.jscode.board.dto.comment;

import com.jscode.board.domain.Board;
import com.jscode.board.domain.Comment;
import com.jscode.board.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentDto {

    private String content;
    private LocalDateTime createdDate;

    @Builder
    public CommentDto(String content, LocalDateTime createdDate) {
        this.content = content;
        this.createdDate = createdDate;
    }

    public static CommentDto from(Comment comment){
        return CommentDto.builder()
                .content(comment.getContent())
                .createdDate(comment.getCreatedDate())
                .build();
    }

    public static Comment toEntity(CommentDto commentDto, Board board, Member member){
        return Comment.builder()
                .content(commentDto.getContent())
                .board(board)
                .member(member)
                .build();
    }
}
