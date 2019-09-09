package com.jha.test.rest.exception.handler;

import com.jha.test.rest.exception.ValueNotFoundException;
import com.jha.test.rest.exception.model.ApiException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException ex){
        return ExceptionHandlerUtil.prepareResponseEntity(ApiException.builder().reason(ex.getMessage()).status(HttpStatus.BAD_REQUEST).code(HttpStatus.BAD_REQUEST.toString()).build());
    }

    @ExceptionHandler(ValueNotFoundException.class)
    public ResponseEntity handleValueNotFoundException(ValueNotFoundException ex){
        return ExceptionHandlerUtil.prepareResponseEntity(ApiException.builder().reason(ex.getMessage()).status(HttpStatus.NOT_FOUND).code(HttpStatus.NOT_FOUND.toString()).build());
    }
}
