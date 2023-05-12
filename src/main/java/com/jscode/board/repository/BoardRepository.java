package com.jscode.board.repository;

import com.jscode.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {

     List<Board> findTop100ByOrderByCreatedDateDesc();

     @Query("select b from Board b where b.title like %:keyword% order by b.createdDate desc")
     List<Board> findByKeyword(@Param("keyword") String keyword);
}
