package com.jscode.board.service;

import com.jscode.board.domain.Board;
import com.jscode.board.domain.Comment;
import com.jscode.board.domain.Member;
import com.jscode.board.dto.board.BoardAndCommentsDto;
import com.jscode.board.dto.board.BoardRequest;
import com.jscode.board.dto.board.BoardResponse;
import com.jscode.board.exception.board.NoAuthorityMemberException;
import com.jscode.board.exception.board.NotFoundBoardException;
import com.jscode.board.repository.BoardRepository;
import com.jscode.board.repository.CommentRepository;
import com.jscode.board.repository.MemberRepository;
import com.jscode.board.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    /**
     * 게시글 생성
     */
    public BoardResponse saveBoard(BoardRequest request) {
        Member member = memberRepository.getOne(SecurityUtil.getCurrentMemberId());
        Board newBoard = new Board(request.getTitle(), request.getContent(), member);
        return BoardResponse.from(boardRepository.save(newBoard));
    }

    /**
     * 게시글 전체 조회
     * 최근 작성 100개
     */
    public List<BoardResponse> findBoards() {
        return boardRepository.findTop100ByOrderByCreatedDateDesc()
                .stream()
                .map(m -> BoardResponse.from(m))
                .collect(Collectors.toList());
    }

    /**
     * id로 게시글 조회
     */
    public BoardAndCommentsDto findBoardById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(NotFoundBoardException::new);
        List<Comment> comments = commentRepository.findAllByBoard(board);
        BoardAndCommentsDto boardAndCommentsDto = BoardAndCommentsDto.from(board, comments);
        return boardAndCommentsDto;
    }

    /**
     * Title 기준 keyword로
     * 최근 작성 게시글 100개 조회
     */
    public List<BoardResponse> findBoardByKeyword(String keyword) {
        return boardRepository.findByKeyword(keyword)
                .stream()
                .map(m -> BoardResponse.from(m))
                .collect(Collectors.toList());
    }

    /**
     * 게시글 내용 변경
     */
    public BoardResponse updateBoard(Long id, BoardRequest request) {
        Long currentMemberId = SecurityUtil.getCurrentMemberId();
        Board board = boardRepository.findById(id).orElseThrow(NotFoundBoardException::new);
        if(currentMemberId != board.getMember().getId()){
            throw new NoAuthorityMemberException();
        }
        board.updateTitle(request.getTitle());
        board.updateContent(request.getContent());
        return BoardResponse.from(board);
    }

    /**
     * 게시글 삭제
     */
    public Long deleteBoard(Long id){
        Long currentMemberId = SecurityUtil.getCurrentMemberId();
        Board board = boardRepository.findById(id).orElseThrow(NotFoundBoardException::new);
        if(currentMemberId != board.getMember().getId()){
            throw new NoAuthorityMemberException();
        }
        boardRepository.delete(board);
        return board.getId();
    }
}
