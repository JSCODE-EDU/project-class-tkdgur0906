package com.jscode.board.dto.comment;

import com.jscode.board.domain.Comment;
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
        CommentDto commentDto = new CommentDto();
        commentDto.content = comment.getContent();
        commentDto.createdDate = comment.getCreatedDate();
        return commentDto;

    }
}
