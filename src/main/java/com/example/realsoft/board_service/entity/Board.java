package com.example.realsoft.board_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boardId;

    private String title;
    private Long userId;
    private Set<Long> memberIds;
    private LocalDate createdAt;
}
