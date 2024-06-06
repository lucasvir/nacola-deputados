package br.com.lucasvir.nacola_deputados.model.dtos;

public record DeputadosDTO(
        Long id,
        String nome,
        String email,
        String siglaPartido,
        String siglaUf,
        String urlFoto
) {
}
