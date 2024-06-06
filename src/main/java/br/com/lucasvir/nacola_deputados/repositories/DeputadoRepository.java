package br.com.lucasvir.nacola_deputados.repositories;

import br.com.lucasvir.nacola_deputados.model.entities.Deputado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeputadoRepository extends JpaRepository<Deputado, Long> {
}
