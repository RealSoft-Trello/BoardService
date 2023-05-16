package com.example.realsoft.board_service.repository;

import com.example.realsoft.board_service.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
