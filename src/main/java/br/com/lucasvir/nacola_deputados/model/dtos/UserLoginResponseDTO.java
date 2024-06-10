package br.com.lucasvir.nacola_deputados.model.dtos;

public record UserLoginResponseDTO(
        String token,
        Long expiresIn
) {
}
