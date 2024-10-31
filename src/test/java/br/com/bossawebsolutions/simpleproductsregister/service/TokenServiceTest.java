package br.com.bossawebsolutions.simpleproductsregister.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;

class TokenServiceTest {

    private TokenService tokenService;
    private JwtEncoder jwtEncoderMock;

    @BeforeEach
    void setUp() {
        jwtEncoderMock = mock(JwtEncoder.class);
        tokenService = new TokenService(jwtEncoderMock);
    }

    @Test
    void testGenerateToken() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("test_user");
        Collection<GrantedAuthority> authorities = Collections.singleton((GrantedAuthority) () -> "ROLE_USER");
        when(authentication.getAuthorities()).thenAnswer(invocation  -> authorities);

        Instant now = Instant.now();
        Instant expiration = now.plus(1, ChronoUnit.DAYS);
        Jwt expectedJwt = createMockJwt("mock-token", now, expiration);

        when(jwtEncoderMock.encode(Mockito.any())).thenReturn(expectedJwt);
        String generatedToken = tokenService.generateToken(authentication);

        assertEquals("mock-token", generatedToken);
    }

    private Jwt createMockJwt(String tokenValue, Instant issuedAt, Instant expiresAt) {
        return Jwt.withTokenValue(tokenValue)
                .header("alg", "RS256")
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .claim("scope", "ROLE_USER")
                .build();
    }
}

