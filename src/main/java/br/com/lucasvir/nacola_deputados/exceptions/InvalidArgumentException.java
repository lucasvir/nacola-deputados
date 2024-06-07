package br.com.lucasvir.nacola_deputados.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidArgumentException extends RuntimeException {

    private final HttpStatus statusCode;

    public InvalidArgumentException(String argumento) {
        super("Argumento inválido. - Argumento: " + argumento);
        this.statusCode = HttpStatus.BAD_REQUEST;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
