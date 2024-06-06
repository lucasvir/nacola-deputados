package br.com.lucasvir.nacola_deputados.services;

import br.com.lucasvir.nacola_deputados.model.dtos.DeputadosDTO;
import br.com.lucasvir.nacola_deputados.model.dtos.DeputadosResultDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ConsumoDeputadosApiService {

    private final String BASE_URL = "https://dadosabertos.camara.leg.br/api/v2/";
    private final String DEPUTADOS_POINT = "deputados?siglaUf=";

    public List<DeputadosDTO> consumirDeputados(String siglaUf) {
       RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DeputadosResultDTO> response =
                restTemplate.getForEntity(BASE_URL + DEPUTADOS_POINT + siglaUf, DeputadosResultDTO.class);

        if(response.getBody() == null) throw new IllegalArgumentException(siglaUf);

        return response.getBody().dados();
    }
}
