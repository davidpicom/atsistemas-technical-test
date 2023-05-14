package com.dpico.atsistemas.prices.application.exception;

import org.springframework.core.*;
import org.springframework.core.annotation.*;
import org.springframework.core.convert.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;

import java.util.*;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerExceptionHandler {

    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage conversionFailedException(ConversionFailedException e, WebRequest request) {
        return ErrorMessage.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .date(new Date())
                .message("Failed to convert date. Pattern expected (yyyy-MM-dd-HH.mm.ss)")
                .description(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage recordNotFoundException(RecordNotFoundException e, WebRequest request) {
        return ErrorMessage.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .date(new Date())
                .message(e.getMessage())
                .description(request.getDescription(false))
                .build();
    }

}
