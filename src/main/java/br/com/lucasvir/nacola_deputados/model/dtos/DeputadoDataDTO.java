package br.com.lucasvir.nacola_deputados.model.dtos;

public record DeputadoDataDTO(
        String email,
        Long id,
        Long idLegislatur,
        String nome,
        String siglaPartido,
        String siglaUf,
        String uri,
        String uriPartido,
        String urlFoto

) {
}
