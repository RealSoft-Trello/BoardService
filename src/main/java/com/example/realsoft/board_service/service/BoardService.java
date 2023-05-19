package com.example.realsoft.board_service.service;

import com.example.realsoft.board_service.exception.BoardNotFound;
import com.example.realsoft.board_service.model.BoardDto;
import com.example.realsoft.board_service.model.ListDto;

import java.util.List;

public interface BoardService {
    List<BoardDto> getAllBoards();
    BoardDto getBoardById(Long boardId) throws BoardNotFound;
    BoardDto createBoard(BoardDto boardDto);
    BoardDto editBoard(Long boardId, BoardDto boardDto) throws BoardNotFound;
    void deleteBoard(Long boardId) throws BoardNotFound;
    List<BoardDto> getBoardsByUserId(Long userId);
    List<ListDto> getListsByBoardId(Long boardId) throws BoardNotFound;
}
