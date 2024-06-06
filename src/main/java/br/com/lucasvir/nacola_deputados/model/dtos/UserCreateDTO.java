package br.com.lucasvir.nacola_deputados.model.dtos;

public record UserCreateDTO(
        String name,
        String email,
        String password,
        String siglaUf
) {
}
