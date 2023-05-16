package com.example.realsoft.board_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class BoardNotFound extends Exception {
    private final String fieldName;
    private final Long fieldValue;

    public BoardNotFound(String fieldName, Long fieldValue) {
        super(String.format("Board not found with %s : %s", fieldName, fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
