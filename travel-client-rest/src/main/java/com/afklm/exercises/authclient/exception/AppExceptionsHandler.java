package com.afklm.exercises.authclient.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
        public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){
            String errorMessageDescription =ex.getLocalizedMessage();
            if(errorMessageDescription == null ) errorMessageDescription=ex.toString();
            ErrorMessage errorMessage = new ErrorMessage(LocalDate.now(),errorMessageDescription);
            return new ResponseEntity<>(errorMessage,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> handleNullPointerException(Exception ex){
        String errorMessageDescription =ex.getLocalizedMessage();
        if(errorMessageDescription == null ) errorMessageDescription=ex.toString();
        ErrorMessage errorMessage = new ErrorMessage(LocalDate.now(),errorMessageDescription);
        return new ResponseEntity<>(errorMessage,new HttpHeaders(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {FareException.class})
    public ResponseEntity<Object> handleFareException(FareException ex, WebRequest request){
        String errorMessageDescription =ex.getLocalizedMessage();
        if(errorMessageDescription == null ) errorMessageDescription=ex.toString();

        ErrorMessage errorMessage = new ErrorMessage(LocalDate.now(),errorMessageDescription);
        return new ResponseEntity<>(errorMessage,new HttpHeaders(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request){
        return new ResponseEntity<>("You don;t have access",new HttpHeaders(),HttpStatus.FORBIDDEN);
    }



}
