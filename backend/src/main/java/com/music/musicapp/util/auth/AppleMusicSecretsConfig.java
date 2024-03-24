package com.music.musicapp.util.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-secrets.properties")
public class AppleMusicSecretsConfig {

    @Value("${apple-music.key-id}")
    private String keyId;

    @Value("${apple-music.team-id}")
    private String teamId;

    @Value("${apple-music.private-key}")
    private String privateKey;

    public String getKeyId() {
        return keyId;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getPrivateKey() {
        return privateKey;
    }
}