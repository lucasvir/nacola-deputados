package br.com.lucasvir.nacola_deputados.model;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ControllerStrategy<T,K> {

   <T> ResponseEntity<List<T>> index();

   <T> ResponseEntity<T> create(K k);

    <T> ResponseEntity<T> show(Long id);
}
