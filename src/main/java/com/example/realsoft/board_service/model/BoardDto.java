package com.example.realsoft.board_service.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private Long boardId;
    private String title;
    private Long userId;
    private Set<Long> memberIds;
    private LocalDate createdAt;
}
