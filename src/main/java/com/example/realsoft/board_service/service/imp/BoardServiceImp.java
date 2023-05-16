package com.example.realsoft.board_service.service.imp;

import com.example.realsoft.board_service.entity.Board;
import com.example.realsoft.board_service.exception.BoardNotFound;
import com.example.realsoft.board_service.model.BoardDto;
import com.example.realsoft.board_service.repository.BoardRepository;
import com.example.realsoft.board_service.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImp implements BoardService {

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<BoardDto> getAllBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(board -> modelMapper.map(board, BoardDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BoardDto getBoardById(Long boardId) throws BoardNotFound {
        return modelMapper.map(findBoard(boardId), BoardDto.class);
    }

    @Override
    public BoardDto createBoard(BoardDto boardDto) {
        Board board = new Board();
        board.setTitle(boardDto.getTitle());
        board.setUserId(boardDto.getUserId());
        board.setMemberIds(boardDto.getMemberIds());
        board.setCreatedAt(LocalDate.now());

        boardRepository.save(board);
        return modelMapper.map(board, BoardDto.class);
    }

    @Override
    public BoardDto editBoard(Long boardId, BoardDto boardDto) throws BoardNotFound {
        Board board = findBoard(boardId);
        board.setTitle(boardDto.getTitle());
        board.setUserId(boardDto.getUserId());
        board.setMemberIds(boardDto.getMemberIds());
        boardRepository.save(board);
        return modelMapper.map(board, BoardDto.class);
    }

    @Override
    public void deleteBoard(Long boardId) throws BoardNotFound {
        boardRepository.delete(findBoard(boardId));
    }

    private Board findBoard(Long boardId) throws BoardNotFound {
        return boardRepository.findById(boardId).orElseThrow(() ->
                new BoardNotFound("Id", boardId));
    }
}
