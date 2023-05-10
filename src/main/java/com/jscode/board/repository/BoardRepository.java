package com.jscode.board.repository;

import com.jscode.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {

     List<Board> findTop100ByOrderByCreatedDateDesc();
}
