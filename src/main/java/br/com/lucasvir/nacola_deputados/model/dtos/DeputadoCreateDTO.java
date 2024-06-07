package br.com.lucasvir.nacola_deputados.model.dtos;

public record DeputadoCreateDTO(
        String name,
        String email,
        String siglaPartido,
        String siglaUf,
        String urlFoto
) {
}
