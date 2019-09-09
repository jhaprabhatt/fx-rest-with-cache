package com.jha.fx.rest.exception.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
@AllArgsConstructor
@Builder
public class ApiException {

    private String code;
    private HttpStatus status;
    private String reason;
}
