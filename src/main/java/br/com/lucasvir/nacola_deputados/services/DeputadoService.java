package br.com.lucasvir.nacola_deputados.services;

import br.com.lucasvir.nacola_deputados.exceptions.EmptyResourceException;
import br.com.lucasvir.nacola_deputados.exceptions.ResourceNotFound;
import br.com.lucasvir.nacola_deputados.model.dtos.DeputadoDataDTO;
import br.com.lucasvir.nacola_deputados.model.dtos.DeputadoResponseDTO;
import br.com.lucasvir.nacola_deputados.model.dtos.DeputadosResultDTO;
import br.com.lucasvir.nacola_deputados.model.entities.Deputado;
import br.com.lucasvir.nacola_deputados.repositories.DeputadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeputadoService {

    @Autowired
    private DeputadoRepository deputadoRepository;

    @Autowired
    ConsumoDeputadosApiService deputadosApi;

    public List<DeputadoResponseDTO> index() {
        List<Deputado> deputados = deputadoRepository.findAll();
        if (deputados.isEmpty()) throw new EmptyResourceException();

        return deputados.stream().map(DeputadoResponseDTO::new).toList();
    }

    public List<DeputadoResponseDTO> findAllByUf(String uf) {
        List<Deputado> deputados = deputadoRepository.findAllByUf(uf);
        return deputados.stream().map(DeputadoResponseDTO::new).toList();
    }

    public List<DeputadoResponseDTO> create(String siglaUf) {
        List<Deputado> deputados = agregarDeputados(siglaUf);
        return deputados.stream().map(DeputadoResponseDTO::new).toList();
    }

    public DeputadoResponseDTO show(Long id) {
        Deputado deputado = deputadoRepository
                .findById(id).orElseThrow(() -> new ResourceNotFound(id.toString()));

        return new DeputadoResponseDTO(deputado);
    }

    public List<Deputado> agregarDeputados(String siglaUf) {
        List<Deputado> deputadosEntities;
        boolean deputadosUfExists = deputadoRepository.existsBySiglaUf(siglaUf);

        if (deputadosUfExists) {
            deputadosEntities = deputadoRepository.findAllByUf(siglaUf);
        } else {
            List<DeputadoDataDTO> deputadosData = deputadosApi.consumirDeputados(siglaUf);
            List<Deputado> deputados = deputadosData.stream().map(Deputado::new).toList();
            deputadosEntities = deputadoRepository.saveAll(deputados);
        }

        return deputadosEntities;
    }
}
