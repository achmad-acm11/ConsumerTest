package com.example.javasalttest.exception;

import jakarta.validation.ConstraintViolation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleApiRequestException(RuntimeException e){
        if (e instanceof NotFoundException){
            Map<String, String> res = new HashMap<>();
            res.put("message", e.getMessage());
            return ResponseEntity.status(404).body(res);
        }
        if (e instanceof BadRequestException){
            var message = ((BadRequestException) e).message;
            return ResponseEntity.status(400).body(message);
        }
        Map<String, String> res = new HashMap<>();
        res.put("message", "Internal Server Error");
        return ResponseEntity.status(500).body(res);
    }
}
