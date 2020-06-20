package com.am.bp.innovations.domain;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class BaseResponse {
    private String message;
    private HttpStatus httpStatus;
}
