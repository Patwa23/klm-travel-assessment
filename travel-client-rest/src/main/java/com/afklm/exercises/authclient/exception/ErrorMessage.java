package com.afklm.exercises.authclient.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private static final long serialVersionUID = 1L;

    private LocalDate localDate;
    private String errorMessageDescription;

}
