package br.com.lucasvir.nacola_deputados.repositories;

import br.com.lucasvir.nacola_deputados.model.entities.Deputado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeputadoRepository extends JpaRepository<Deputado, Long> {

    boolean existsByEmail(String email);

    boolean existsBySiglaUfIgnoreCase(String siglaUf);

    List<Deputado> findAllBySiglaUfIgnoreCase(String sigla);
}
