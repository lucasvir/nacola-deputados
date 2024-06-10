package br.com.lucasvir.nacola_deputados.services;

import br.com.lucasvir.nacola_deputados.exceptions.AlreadyRegisteredExeption;
import br.com.lucasvir.nacola_deputados.exceptions.EmptyResourceException;
import br.com.lucasvir.nacola_deputados.exceptions.ResourceNotFound;
import br.com.lucasvir.nacola_deputados.model.dtos.UserCreateDTO;
import br.com.lucasvir.nacola_deputados.model.dtos.UserResponseDTO;
import br.com.lucasvir.nacola_deputados.model.entities.Deputado;
import br.com.lucasvir.nacola_deputados.model.entities.User;
import br.com.lucasvir.nacola_deputados.model.enums.UnidadeFederativa;
import br.com.lucasvir.nacola_deputados.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeputadoService deputadoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserResponseDTO> index() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) throw new EmptyResourceException();

        return users.stream().map(UserResponseDTO::new).toList();
    }

    public UserResponseDTO create(UserCreateDTO dto) {
        boolean emailExists = userRepository.existsByEmail(dto.email());
        if (emailExists) throw new AlreadyRegisteredExeption(dto.email());

        UnidadeFederativa uf = UnidadeFederativa.fromSigla(dto.siglaUf());
        User user = new User(
                dto.name(),
                dto.email(),
                passwordEncoder.encode(dto.password()),
                uf
        );

        // BUSCAR E GRAVAR DEPUTADOS RELACIONADOS AO UF DO USUARIO
        List<Deputado> deputados = deputadoService.agregarDeputados(uf.getSigla());
        user.setDeputados(deputados);
        User userEntity = userRepository.save(user);

        return new UserResponseDTO(userEntity);
    }

    public UserResponseDTO show(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFound(id.toString()));
        return new UserResponseDTO(user);
    }

    public User showByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFound(email));
        return user;
    }
}
