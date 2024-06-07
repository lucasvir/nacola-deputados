package br.com.lucasvir.nacola_deputados.model.dtos;

import br.com.lucasvir.nacola_deputados.model.entities.Deputado;

public record DeputadoResponseDTO(
        Long id,
        String name,
        String email,
        String siglaPartido,
        String siglaUf,
        String urlFoto
) {

    public DeputadoResponseDTO(Deputado deputado) {
        this(
                deputado.getId(),
                deputado.getName(),
                deputado.getEmail(),
                deputado.getSiglaPartido(),
                deputado.getSiglaUf(),
                deputado.getUrlFoto()
        );
    }
}
