package br.com.lucasvir.nacola_deputados.controller;

import br.com.lucasvir.nacola_deputados.model.ControllerStrategy;
import br.com.lucasvir.nacola_deputados.model.dtos.UserCreateDTO;
import br.com.lucasvir.nacola_deputados.model.dtos.UserResponseDTO;
import br.com.lucasvir.nacola_deputados.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController implements ControllerStrategy<UserResponseDTO, UserCreateDTO> {

    @Autowired
    private UserService userService;

    @Override
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> index() {
        List<UserResponseDTO> usersDTO = userService.index();
        return ResponseEntity.ok(usersDTO);
    }

    @Override
    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserCreateDTO dto) {
        UserResponseDTO user = userService.create(dto);
        URI uri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.id()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> show(@PathVariable Long id) {
        UserResponseDTO user = userService.show(id);
        return ResponseEntity.ok(user);
    }

}
