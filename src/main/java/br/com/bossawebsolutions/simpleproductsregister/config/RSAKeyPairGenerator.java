package br.com.bossawebsolutions.simpleproductsregister.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class RSAKeyPairGenerator {

    private final KeyPair keyPair;

    public RSAKeyPairGenerator() throws NoSuchAlgorithmException {
    	KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        keyPairGenerator.initialize(2048);

        keyPair = keyPairGenerator.generateKeyPair();
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }
}
