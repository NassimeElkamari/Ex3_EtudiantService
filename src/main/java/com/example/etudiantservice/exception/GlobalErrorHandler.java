package com.example.etudiantservice.exception;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus
    public Map<String, Object> handleRSE(ResponseStatusException ex) {
        assert ex.getReason() != null;
        return Map.of(
                "message", ex.getReason()
        );
    }
}
