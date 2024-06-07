package br.com.lucasvir.nacola_deputados.services;

import br.com.lucasvir.nacola_deputados.model.dtos.DeputadoDataDTO;
import br.com.lucasvir.nacola_deputados.model.dtos.DeputadosResultDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ConsumoDeputadosApiService {

    private final String BASE_URL;
    private final String DEPUTADOS_POINT;

    public ConsumoDeputadosApiService() {
        this.BASE_URL = "https://dadosabertos.camara.leg.br/api/v2/";
        this.DEPUTADOS_POINT = "deputados?siglaUf=";
    }

    public List<DeputadoDataDTO> consumirDeputados(String siglaUf) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<DeputadosResultDTO> response =
                    restTemplate.getForEntity(BASE_URL + DEPUTADOS_POINT + siglaUf, DeputadosResultDTO.class);

            if (response.getBody() == null) throw new IllegalArgumentException(siglaUf);

            return response.getBody().dados();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        return null;
    }
}
