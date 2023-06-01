package com.jscode.board.repository;

import com.jscode.board.domain.Board;
import com.jscode.board.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c JOIN FETCH c.member m JOIN FETCH c.board b WHERE b = :board")
    List<Comment> findAllByBoardAndMember(@Param("board") Board board);
}
