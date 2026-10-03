package com.fiap.soat.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private List<String> errors;

}
