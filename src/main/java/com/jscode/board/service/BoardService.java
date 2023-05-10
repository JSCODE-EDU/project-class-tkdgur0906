package com.jscode.board.service;

import com.jscode.board.domain.Board;
import com.jscode.board.dto.request.BoardRequest;
import com.jscode.board.dto.response.BoardResponse;
import com.jscode.board.exception.board.NotFoundBoardException;
import com.jscode.board.repository.BoardRepository;
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

    public BoardResponse saveBoard(BoardRequest request) {
        Board savedBoard = boardRepository.save(BoardResponse.toEntity(request));
        return BoardResponse.from(savedBoard);
    }

    public List<BoardResponse> findBoards() {
        return boardRepository.findAll()
                .stream().map(m -> BoardResponse.from(m))
                .collect(Collectors.toList());
    }

    public BoardResponse findBoardById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(NotFoundBoardException::new);
        return BoardResponse.from(board);
    }

    public BoardResponse updateBoard(Long id, BoardRequest request) {
        Board board = boardRepository.findById(id).orElseThrow(NotFoundBoardException::new);
        board.updateTitle(request.getTitle());
        board.updateContent(request.getContent());
        return BoardResponse.from(board);
    }

    public Long deleteBoard(Long id){
        Board board = boardRepository.findById(id).orElseThrow(NotFoundBoardException::new);
        boardRepository.delete(board);
        return board.getId();
    }
}
