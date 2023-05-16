package com.example.realsoft.board_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private String title;
    private Long userId;
    private Set<Long> memberIds;
    private LocalDate createdAt;
}
