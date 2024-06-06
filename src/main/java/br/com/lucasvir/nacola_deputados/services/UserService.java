package br.com.lucasvir.nacola_deputados.services;

import br.com.lucasvir.nacola_deputados.model.dtos.DeputadosDTO;
import br.com.lucasvir.nacola_deputados.model.dtos.UserCreateDTO;
import br.com.lucasvir.nacola_deputados.model.dtos.UserResponseDTO;
import br.com.lucasvir.nacola_deputados.model.entities.Deputado;
import br.com.lucasvir.nacola_deputados.model.entities.User;
import br.com.lucasvir.nacola_deputados.model.enums.UnidadeFederativa;
import br.com.lucasvir.nacola_deputados.repositories.DeputadoRepository;
import br.com.lucasvir.nacola_deputados.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeputadoRepository deputadoRepository;

    @Autowired
    private ConsumoDeputadosApiService deputadosApi;

    public List<UserResponseDTO> index() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) throw new RuntimeException("Não há usuários cadastrados.");

        return users.stream().map(UserResponseDTO::new).toList();
    }

    public UserResponseDTO create(UserCreateDTO dto) {
        boolean emailExists = userRepository.existsByEmail(dto.email());
        if (emailExists) throw new RuntimeException("Email já cadastrado.");

        UnidadeFederativa uf = UnidadeFederativa.fromSigla(dto.siglaUf());
        User user = new User(
                dto.name(),
                dto.email(),
                dto.password(),
                uf
        );

        // BUSCAR E GRAVAR DEPUTADOS RELACIONADOS AO UF DO USUARIO
        List<Deputado> deputados = gravarListaDeDeputadosPorUf(uf.getSigla());
        user.setDeputados(deputados);
        User userEntity = userRepository.save(user);

        return new UserResponseDTO(userEntity);
    }

    public UserResponseDTO show(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id.toString()));
        return new UserResponseDTO(user);
    }

    private List<Deputado> gravarListaDeDeputadosPorUf(String siglaUf) {
        List<DeputadosDTO> deputados = deputadosApi.consumirDeputados(siglaUf);
        List<Deputado> deputadosEntities = deputados.stream().map(Deputado::new).toList();
        return deputadoRepository.saveAll(deputadosEntities);
    }
}
