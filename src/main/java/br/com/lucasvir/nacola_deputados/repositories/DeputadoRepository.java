package br.com.lucasvir.nacola_deputados.repositories;

import br.com.lucasvir.nacola_deputados.model.entities.Deputado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeputadoRepository extends JpaRepository<Deputado, Long> {

    boolean existsByEmail(String email);

    boolean existsBySiglaUf(String siglaUf);

    @Query("SELECT d FROM Deputado d WHERE d.siglaUf = :sigla")
    List<Deputado> findAllByUf(String sigla);
}
