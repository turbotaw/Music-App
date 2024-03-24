package com.music.musicapp.util.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@Component
public class AppleMusicTokenGenerator {

    private final AppleMusicSecretsConfig appleMusicSecretsConfig;

    @Autowired
    public AppleMusicTokenGenerator(AppleMusicSecretsConfig appleMusicSecretsConfig) {
        this.appleMusicSecretsConfig = appleMusicSecretsConfig;
    }

    public String generateToken() throws Exception {
        String keyId = appleMusicSecretsConfig.getKeyId();
        String teamId = appleMusicSecretsConfig.getTeamId();
        String privateKeyStr = appleMusicSecretsConfig.getPrivateKey();

        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyStr.replaceAll("-----END PRIVATE KEY-----", "")
                                                                      .replaceAll("-----BEGIN PRIVATE KEY-----", "")
                                                                      .replaceAll("\\s", ""));

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PrivateKey key = keyFactory.generatePrivate(keySpec);

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date expiryDate = new Date(nowMillis + 15777000L * 1000); // 6 months in milliseconds

        return Jwts.builder()
                   .setHeaderParam("kid", keyId)
                   .setIssuedAt(now)
                   .setExpiration(expiryDate)
                   .setIssuer(teamId)
                   .signWith(SignatureAlgorithm.ES256, key)
                   .compact();
    }
}