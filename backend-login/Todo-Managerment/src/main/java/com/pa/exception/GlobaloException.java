package com.pa.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class GlobaloException {
	@ExceptionHandler(TodoAPIException.class)
    public ResponseEntity<ErrorDetail> handleTodoAPIException(TodoAPIException exception,
                                                               WebRequest webRequest){

        ErrorDetail errorDetails = new ErrorDetail(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
