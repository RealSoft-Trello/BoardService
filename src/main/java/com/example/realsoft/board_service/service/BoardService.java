package com.example.realsoft.board_service.service;

import com.example.realsoft.board_service.exception.BoardNotFound;
import com.example.realsoft.board_service.model.BoardDto;

import java.util.List;

public interface BoardService {
    List<BoardDto> getAllBoards();
    BoardDto getBoardById(Long boardId) throws BoardNotFound;
    BoardDto createBoard(BoardDto boardDto);
    BoardDto editBoard(Long boardId, BoardDto boardDto) throws BoardNotFound;
    void deleteBoard(Long boardId) throws BoardNotFound;
}
