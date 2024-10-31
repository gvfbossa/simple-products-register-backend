package br.com.bossawebsolutions.simpleproductsregister.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import br.com.bossawebsolutions.simpleproductsregister.service.TokenService;

class AuthControllerTest {

    private TokenService tokenServiceMock;
    private AuthController authController;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        tokenServiceMock = mock(TokenService.class);
        authController = new AuthController(tokenServiceMock);

        Field tokenServiceField = AuthController.class.getDeclaredField("tokenService");
        tokenServiceField.setAccessible(true);
        tokenServiceField.set(authController, tokenServiceMock);
    }

    @Test
    void testToken() {
        String mockToken = "mock-token";
        Authentication authenticationMock = mock(Authentication.class);

        when(tokenServiceMock.generateToken(authenticationMock)).thenReturn(mockToken);

        ResponseEntity<String> response = authController.token(authenticationMock);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockToken, response.getBody());
    }
}
