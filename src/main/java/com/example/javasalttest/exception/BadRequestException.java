package com.example.javasalttest.exception;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class BadRequestException extends RuntimeException {
    public final Map<String, String> message;
    public BadRequestException(String msg, Map<String, String> message){
        super(msg);
        this.message = message;
    }
}
