package com.jscode.board.service;

import com.jscode.board.domain.Board;
import com.jscode.board.domain.Member;
import com.jscode.board.dto.board.BoardRequest;
import com.jscode.board.dto.board.BoardResponse;
import com.jscode.board.exception.board.NotFoundBoardException;
import com.jscode.board.jwt.TokenProvider;
import com.jscode.board.repository.BoardRepository;
import com.jscode.board.repository.MemberRepository;
import com.jscode.board.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
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
    public BoardResponse findBoardById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(NotFoundBoardException::new);
        return BoardResponse.from(board);
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
        Board board = boardRepository.findById(id).orElseThrow(NotFoundBoardException::new);
        board.updateTitle(request.getTitle());
        board.updateContent(request.getContent());
        return BoardResponse.from(board);
    }

    /**
     * 게시글 삭제
     */
    public Long deleteBoard(Long id){
        Board board = boardRepository.findById(id).orElseThrow(NotFoundBoardException::new);
        boardRepository.delete(board);
        return board.getId();
    }
}
