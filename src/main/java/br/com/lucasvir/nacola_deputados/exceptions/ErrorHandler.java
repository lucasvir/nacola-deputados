package br.com.lucasvir.nacola_deputados.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EmptyResourceException.class)
    private ResponseEntity<String> handle404EmptyResourceException(EmptyResourceException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(AlreadyRegisteredExeption.class)
    private ResponseEntity<String> handle400AlredyRegisteredException(AlreadyRegisteredExeption ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFound.class)
    private ResponseEntity<String> handle400AlredyRegisteredException(ResourceNotFound ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }
}
