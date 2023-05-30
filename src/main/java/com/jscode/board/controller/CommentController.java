package com.jscode.board.controller;

import com.jscode.board.dto.comment.CommentDto;
import com.jscode.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards/{boardId}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Long> commentAdd(@PathVariable Long boardId, @RequestBody CommentDto commentDto) {
        Long id = commentService.saveComment(boardId, commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }
}
