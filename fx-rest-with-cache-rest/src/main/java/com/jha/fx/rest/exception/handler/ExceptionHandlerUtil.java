package com.jha.fx.rest.exception.handler;

import com.jha.fx.rest.exception.model.ApiException;
import org.springframework.http.ResponseEntity;

public class ExceptionHandlerUtil {

    public static final ResponseEntity prepareResponseEntity(final ApiException apiException) {
        return new ResponseEntity(apiException, apiException.getStatus());
    }
}
