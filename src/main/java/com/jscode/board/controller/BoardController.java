package com.jscode.board.controller;

import com.jscode.board.dto.request.BoardRequest;
import com.jscode.board.dto.response.BoardResponse;
import com.jscode.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardResponse> boardAdd(@Valid @RequestBody BoardRequest request){
        BoardResponse response = boardService.saveBoard(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponse>> boardList(){
        List<BoardResponse> response = boardService.findBoards();
        return ResponseEntity.ok(response);
    }

    @GetMapping(params = "keyword")
    public ResponseEntity<List<BoardResponse>> boardListByKeyword(@RequestParam String keyword){
        List<BoardResponse> response = boardService.findBoardByKeyword(keyword);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> boardDetails(@PathVariable Long id){
        BoardResponse response = boardService.findBoardById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponse> boardModify(@PathVariable Long id, @RequestBody BoardRequest request){
        BoardResponse response = boardService.updateBoard(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> boardRemove(@PathVariable Long id){
        Long deletedId = boardService.deleteBoard(id);
        return ResponseEntity.ok(deletedId);
    }


}
