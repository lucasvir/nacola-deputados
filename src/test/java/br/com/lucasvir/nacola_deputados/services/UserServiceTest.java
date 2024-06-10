package br.com.lucasvir.nacola_deputados.services;

import br.com.lucasvir.nacola_deputados.controller.UserController;
import br.com.lucasvir.nacola_deputados.exceptions.EmptyResourceException;
import br.com.lucasvir.nacola_deputados.model.dtos.UserResponseDTO;
import br.com.lucasvir.nacola_deputados.model.entities.User;
import br.com.lucasvir.nacola_deputados.model.enums.UnidadeFederativa;
import br.com.lucasvir.nacola_deputados.repositories.DeputadoRepository;
import br.com.lucasvir.nacola_deputados.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DeputadoRepository deputadoRepository;

    private final List<User> userMockList = new ArrayList<>();

    @BeforeEach
    public void init() {
        User userA = new User("Teste A", "testeA@email.com", "123", UnidadeFederativa.ACRE);
        User userB = new User("Teste B", "testeB@email.com", "123", UnidadeFederativa.ACRE);
        User userC = new User("Teste C", "testeC@email.com", "123", UnidadeFederativa.ACRE);

        userMockList.add(userA);
        userMockList.add(userB);
        userMockList.add(userC);
    }

    @Test
    public void shouldReturnAllUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(this.userMockList);

        //test
        List<UserResponseDTO> indexResult = userService.index();

        assertEquals(3, indexResult.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void shouldReturnListUserResponseDTO() {
        when(userRepository.findAll()).thenReturn(this.userMockList);

        // test
        List<UserResponseDTO> actualResult = userService.index();

        assertEquals(UserResponseDTO.class, actualResult.get(0).getClass());
    }

    @Test
    public void shouldThrowEmptyResourceException() {
        List<User> empyList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(empyList);

        //test
        EmptyResourceException exception = assertThrows(EmptyResourceException.class, () -> userService.index());
        String expectedMessage = "Não há registros.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}

