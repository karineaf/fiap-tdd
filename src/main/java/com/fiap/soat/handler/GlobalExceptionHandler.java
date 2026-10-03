package com.fiap.soat.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

// Captura todos os erros dentro da requisição
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<String> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors())
            errors.add(error.getField() + ": " + error.getDefaultMessage());

        Collections.sort(errors);

        ErrorResponse errorResponse = new ErrorResponse("Validation error", errors);

        return ResponseEntity
                .badRequest()
                .contentType(APPLICATION_JSON)
                .body(errorResponse);
    }
}
