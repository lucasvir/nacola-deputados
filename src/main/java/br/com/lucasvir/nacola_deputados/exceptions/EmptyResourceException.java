package br.com.lucasvir.nacola_deputados.exceptions;

import org.springframework.http.HttpStatus;

public class EmptyResourceException extends RuntimeException {

    private final HttpStatus statusCode;

    public EmptyResourceException() {
        super("Não há registros.");
        this.statusCode = HttpStatus.NOT_FOUND;
    }


    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
