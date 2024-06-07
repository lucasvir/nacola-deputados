package br.com.lucasvir.nacola_deputados.services;

import br.com.lucasvir.nacola_deputados.exceptions.EmptyResourceException;
import br.com.lucasvir.nacola_deputados.exceptions.InvalidArgumentException;
import br.com.lucasvir.nacola_deputados.exceptions.ResourceNotFound;
import br.com.lucasvir.nacola_deputados.model.dtos.DeputadoDataDTO;
import br.com.lucasvir.nacola_deputados.model.dtos.DeputadoResponseDTO;
import br.com.lucasvir.nacola_deputados.model.entities.Deputado;
import br.com.lucasvir.nacola_deputados.model.enums.UnidadeFederativa;
import br.com.lucasvir.nacola_deputados.repositories.DeputadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    public List<DeputadoResponseDTO> indexAllByUf(String uf) {
        checkSiglaExists(uf);

        List<Deputado> deputados = deputadoRepository.findAllBySiglaUfIgnoreCase(uf);
        if (deputados.isEmpty()) throw new EmptyResourceException();

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
        checkSiglaExists(siglaUf);

        boolean existsUf = deputadoRepository.existsBySiglaUfIgnoreCase(siglaUf);

        if (!existsUf) {
            List<DeputadoDataDTO> deputadosData = deputadosApi.consumirDeputados(siglaUf);
            if (deputadosData == null)
                throw new RuntimeException("Erro ao consumir api externa. Argumento: " + siglaUf);

            List<Deputado> deputados = deputadosData.stream().map(Deputado::new).toList();
            return deputadoRepository.saveAll(deputados);
        } else {
            var deputadosss = deputadoRepository.findAllBySiglaUfIgnoreCase(siglaUf);
            return  deputadosss;
        }
    }

    private void checkSiglaExists(String siglaUf) {
        boolean siglaExists = Arrays.stream(UnidadeFederativa.values()).anyMatch(u -> u.getSigla().equalsIgnoreCase(siglaUf));
        if (!siglaExists) throw new InvalidArgumentException(siglaUf);
    }
}
