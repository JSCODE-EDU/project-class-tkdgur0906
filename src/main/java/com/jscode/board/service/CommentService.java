package com.jscode.board.service;

import com.jscode.board.domain.Board;
import com.jscode.board.domain.Comment;
import com.jscode.board.domain.Member;
import com.jscode.board.dto.comment.CommentDto;
import com.jscode.board.exception.board.NotFoundBoardException;
import com.jscode.board.repository.BoardRepository;
import com.jscode.board.repository.CommentRepository;
import com.jscode.board.repository.MemberRepository;
import com.jscode.board.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    public Long saveComment(Long boardId, CommentDto commentDto) {
        Member member = memberRepository.getOne(SecurityUtil.getCurrentMemberId());
        Board board = boardRepository.findById(boardId).orElseThrow(NotFoundBoardException::new);
        Comment comment = commentRepository.save(CommentDto.toEntity(commentDto, board, member));
        return comment.getId();
    }
}
