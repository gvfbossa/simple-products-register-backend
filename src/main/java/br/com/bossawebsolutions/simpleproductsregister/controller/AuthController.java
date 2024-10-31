package br.com.bossawebsolutions.simpleproductsregister.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bossawebsolutions.simpleproductsregister.service.TokenService;

@RestController
@RequestMapping
public class AuthController {

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> token(Authentication authentication) {
        String token = tokenService.generateToken(authentication);
        return ResponseEntity.ok(token);
    }

}