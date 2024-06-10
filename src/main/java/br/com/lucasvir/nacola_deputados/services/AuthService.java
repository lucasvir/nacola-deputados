package br.com.lucasvir.nacola_deputados.services;

import br.com.lucasvir.nacola_deputados.model.dtos.UserCreateDTO;
import br.com.lucasvir.nacola_deputados.model.dtos.UserLoginDTO;
import br.com.lucasvir.nacola_deputados.model.dtos.UserLoginResponseDTO;
import br.com.lucasvir.nacola_deputados.model.dtos.UserResponseDTO;
import br.com.lucasvir.nacola_deputados.model.entities.User;
import br.com.lucasvir.nacola_deputados.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserResponseDTO signUp(UserCreateDTO dto) {
        return userService.create(dto);
    }

    public UserLoginResponseDTO authenticate(UserLoginDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.email(),
                        dto.password()
                )
        );

        User user = userService.showByEmail(dto.email());
        String jwtToken = jwtService.generateToken(user);

        return new UserLoginResponseDTO(jwtToken, jwtService.getExpirationTime());
    }
}
