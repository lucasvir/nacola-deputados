package br.com.lucasvir.nacola_deputados.repositories;

import br.com.lucasvir.nacola_deputados.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);
}
