package com.example.realsoft.board_service.controller;

import com.example.realsoft.board_service.exception.BoardNotFound;
import com.example.realsoft.board_service.model.BoardDto;
import com.example.realsoft.board_service.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("realsoft/trello/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<List<BoardDto>> getAllBoards() {
        return ResponseEntity.ok(boardService.getAllBoards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDto> getBoardById(@PathVariable(name = "id") Long boardId) throws BoardNotFound {
        return ResponseEntity.ok(boardService.getBoardById(boardId));
    }

    @PostMapping
    public ResponseEntity<BoardDto> createBoard(@RequestBody BoardDto boardDto) {
        return new ResponseEntity<>(boardService.createBoard(boardDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardDto> editBoard(@PathVariable(name = "id") Long boardId, @RequestBody BoardDto boardDto) throws BoardNotFound {
        return new ResponseEntity<>(boardService.editBoard(boardId, boardDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") Long boardId) throws BoardNotFound {
        boardService.deleteBoard(boardId);
        return new ResponseEntity<>("Board deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/user/{id}" )
    public ResponseEntity<List<BoardDto>> getBoardsByUserId(@PathVariable(name = "id" ) Long userId) {
        return ResponseEntity.ok(boardService.getBoardsByUserId(userId));
    }
}
