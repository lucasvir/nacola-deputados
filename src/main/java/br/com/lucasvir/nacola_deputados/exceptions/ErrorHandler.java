package br.com.lucasvir.nacola_deputados.exceptions;

import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private ResponseEntity<String> handle400ResourceNotFoundException(ResourceNotFound ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidArgumentException.class)
    private ResponseEntity<String> handle400InvalidArgumentException(InvalidArgumentException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }
}
