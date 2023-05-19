package com.example.realsoft.board_service.service.imp;

import com.example.realsoft.board_service.entity.Board;
import com.example.realsoft.board_service.exception.BoardNotFound;
import com.example.realsoft.board_service.model.BoardDto;
import com.example.realsoft.board_service.model.ListDto;
import com.example.realsoft.board_service.repository.BoardRepository;
import com.example.realsoft.board_service.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImp implements BoardService {

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

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

    @Override
    public List<BoardDto> getBoardsByUserId(Long userId) {
        return boardRepository
                .getBoardsByUserId(userId)
                .stream()
                .map(board -> modelMapper.map(board, BoardDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ListDto> getListsByBoardId(Long boardId) throws BoardNotFound {
        Board board = boardRepository.findById(boardId).orElse(null);
        if(board == null) {
            throw new BoardNotFound("Id", boardId);
        }
        String listsUrl = "http://list-service/realsoft/trello/lists/board/" + boardId;
        ResponseEntity<List<ListDto>> response = restTemplate.exchange(
                listsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ListDto>>() {}
        );
        return response.getBody();
    }

    private Board findBoard(Long boardId) throws BoardNotFound {
        return boardRepository.findById(boardId).orElseThrow(() ->
                new BoardNotFound("Id", boardId));
    }
}
