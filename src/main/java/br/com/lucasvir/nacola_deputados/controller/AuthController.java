package br.com.lucasvir.nacola_deputados.controller;

import br.com.lucasvir.nacola_deputados.model.dtos.UserCreateDTO;
import br.com.lucasvir.nacola_deputados.model.dtos.UserLoginDTO;
import br.com.lucasvir.nacola_deputados.model.dtos.UserLoginResponseDTO;
import br.com.lucasvir.nacola_deputados.model.dtos.UserResponseDTO;
import br.com.lucasvir.nacola_deputados.security.JWTService;
import br.com.lucasvir.nacola_deputados.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> singUp(@RequestBody UserCreateDTO dto) {
        UserResponseDTO user = authService.signUp(dto);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> authenticate(@RequestBody UserLoginDTO dto) {
        UserLoginResponseDTO authenticatedUser = authService.authenticate(dto);
        return ResponseEntity.ok(authenticatedUser);
    }
}
