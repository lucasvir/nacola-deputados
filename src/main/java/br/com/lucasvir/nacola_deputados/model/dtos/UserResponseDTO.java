package br.com.lucasvir.nacola_deputados.model.dtos;

import br.com.lucasvir.nacola_deputados.model.entities.Deputado;
import br.com.lucasvir.nacola_deputados.model.entities.User;
import br.com.lucasvir.nacola_deputados.model.enums.UnidadeFederativa;

import java.util.List;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        UnidadeFederativa uf,
        List<Deputado> deputados
) {

    public UserResponseDTO(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUf(),
                user.getDeputados()
        );
    }
}
