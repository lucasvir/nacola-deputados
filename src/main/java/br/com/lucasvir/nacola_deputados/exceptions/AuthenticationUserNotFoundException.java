package br.com.lucasvir.nacola_deputados.exceptions;

import org.springframework.http.HttpStatus;

public class AuthenticationUserNotFoundException extends RuntimeException {

    private final HttpStatus statusCode;

    public AuthenticationUserNotFoundException() {
        super("Credenciais inválidas");
        this.statusCode = HttpStatus.NOT_FOUND;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
