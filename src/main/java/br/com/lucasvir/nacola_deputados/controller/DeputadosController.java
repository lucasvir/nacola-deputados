package br.com.lucasvir.nacola_deputados.controller;

import br.com.lucasvir.nacola_deputados.model.dtos.DeputadoResponseDTO;
import br.com.lucasvir.nacola_deputados.services.ConsumoDeputadosApiService;
import br.com.lucasvir.nacola_deputados.services.DeputadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/deputados")
public class DeputadosController implements ControllerStrategy<DeputadoResponseDTO, String> {

    @Autowired
    private DeputadoService deputadoService;

    @Autowired
    private ConsumoDeputadosApiService deputadosApi;

    @Override
    @GetMapping
    public ResponseEntity<List<DeputadoResponseDTO>> index() {
        List<DeputadoResponseDTO> deputados = deputadoService.index();
        return ResponseEntity.ok(deputados);
    }

    @Override
    @GetMapping("/consumir/{siglaUf}")
    public ResponseEntity<DeputadoResponseDTO> create(@PathVariable String siglaUf) {
        List<DeputadoResponseDTO> deputados = deputadoService.create(siglaUf);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{siglaUf}").buildAndExpand(deputados.get(0).id()).toUri();

        return ResponseEntity.created(uri).body(deputados.get(0));
    }

    @GetMapping("estado/{siglaUf}")
    public ResponseEntity<List<DeputadoResponseDTO>> indexByUf(@PathVariable String siglaUf) {
        List<DeputadoResponseDTO> deputados = deputadoService.indexAllByUf(siglaUf);
        return ResponseEntity.ok(deputados);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DeputadoResponseDTO> show(@PathVariable Long id) {
        DeputadoResponseDTO deputado = deputadoService.show(id);
        return ResponseEntity.ok(deputado);
    }

}
