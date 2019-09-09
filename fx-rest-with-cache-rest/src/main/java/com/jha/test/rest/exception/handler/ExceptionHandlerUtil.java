package com.jha.test.rest.exception.handler;

import com.jha.test.rest.exception.model.ApiException;
import org.springframework.http.ResponseEntity;

public class ExceptionHandlerUtil {

    public static final ResponseEntity prepareResponseEntity(final ApiException apiException) {
        return new ResponseEntity(apiException, apiException.getStatus());
    }
}
