package com.example.realsoft.board_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListDto {
    private Long listId;
    private String title;
    private Long boardId;
    private LocalDate createdAt;
}