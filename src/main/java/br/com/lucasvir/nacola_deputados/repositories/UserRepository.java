package br.com.lucasvir.nacola_deputados.repositories;

import br.com.lucasvir.nacola_deputados.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
