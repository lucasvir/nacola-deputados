package br.com.lucasvir.nacola_deputados.model;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ControllerStrategy<T, K> {

    ResponseEntity<List<T>> index();

    ResponseEntity<T> create(K k);

    ResponseEntity<T> show(Long id);
}
