package br.com.lucasvir.nacola_deputados.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFound extends RuntimeException {

    private final HttpStatus statusCode;

    public ResourceNotFound(String argumento) {
        super("Não há registros. Argumento: " + argumento);
        this.statusCode = HttpStatus.NOT_FOUND;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
