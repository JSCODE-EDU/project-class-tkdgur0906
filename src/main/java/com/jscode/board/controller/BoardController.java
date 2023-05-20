package com.jscode.board.controller;

import com.jscode.board.dto.board.BoardRequest;
import com.jscode.board.dto.board.BoardResponse;
import com.jscode.board.exception.response.ExceptionResponse;
import com.jscode.board.exception.response.FieldExceptionResponse;
import com.jscode.board.service.BoardService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Api(tags = "게시판 API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "게시글 작성 기능", notes = "title, content를 입력받아 게시글 저장")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "생성 성공", response = BoardResponse.class),
            @ApiResponse(code = 400, message = "유효하지 않은 입력입니다.", response = ExceptionResponse.class)
    })
    public ResponseEntity<BoardResponse> boardAdd(@ApiParam(value = "요청 dto")
                                                      @Valid @RequestBody BoardRequest boardRequest){
        BoardResponse response = boardService.saveBoard(boardRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @ApiOperation(value = "게시글 전체 조회 기능", notes = "최근 작성된 순으로 게시글을 최대 100개 조회")
    @ApiResponse(code = 200, message = "조회 성공", response = BoardResponse.class, responseContainer = "List")
    public ResponseEntity<List<BoardResponse>> boardList(){
        List<BoardResponse> response = boardService.findBoards();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    @ApiOperation(value = "게시글 검색 기능", notes = "키워드와 일치하는 부분이 있는 제목을 가진 게시글 조회")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "조회 성공", response = BoardResponse.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "유효하지 않은 입력입니다.", response = ExceptionResponse.class)
    })
    public ResponseEntity<List<BoardResponse>> boardListByKeyword(@ApiParam(value = "keyword 명", example = "hello")
                                                                      @NotBlank @RequestParam String keyword){
        List<BoardResponse> response = boardService.findBoardByKeyword(keyword);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "특정 게시글 조회 기능", notes = "특정 id를 가지는 게시글 조회")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "조회 성공", response = BoardResponse.class),
            @ApiResponse(code = 404, message = "게시글이 존재하지 않습니다.", response = FieldExceptionResponse.class)
    })
    public ResponseEntity<BoardResponse> boardDetails(@ApiParam(value = "게시글 id 명", example = "13")
                                                          @PathVariable(value = "boardId") Long id){
        BoardResponse response = boardService.findBoardById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "특정 게시글 수정 기능", notes = "id, title, content를 입력받아 특정 id를 가지는 게시글 수정")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "수정 성공", response = BoardResponse.class),
            @ApiResponse(code = 400, message = "유효하지 않은 입력입니다.", response = FieldExceptionResponse.class)
    })
    public ResponseEntity<BoardResponse> boardModify(@ApiParam(value = "게시글 id 명", example = "13")
                                                         @PathVariable(value = "boardId") Long id,
                                                     @Valid @RequestBody BoardRequest boardRequest){
        BoardResponse response = boardService.updateBoard(id, boardRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "특정 게시글 삭제 기능", notes = "특정 id를 가지는 게시글 삭제")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "삭제 성공", response = BoardResponse.class),
            @ApiResponse(code = 404, message = "게시글이 존재하지 않습니다.", response = FieldExceptionResponse.class)
    })
    public ResponseEntity<Long> boardRemove(@ApiParam(value = "게시글 id 명", example = "13")
                                                @PathVariable(value = "boardId") Long id){
        Long deletedId = boardService.deleteBoard(id);
        return ResponseEntity.ok(deletedId);
    }


}
