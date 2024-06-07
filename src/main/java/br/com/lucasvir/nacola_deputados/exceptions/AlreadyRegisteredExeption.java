package br.com.lucasvir.nacola_deputados.exceptions;

import org.springframework.http.HttpStatus;

public class AlreadyRegisteredExeption extends RuntimeException {

    private final HttpStatus statusCode;

    public AlreadyRegisteredExeption(String argumento) {
        super("Já registrado. - Argumento: " + argumento);
        this.statusCode = HttpStatus.BAD_REQUEST;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
