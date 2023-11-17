package com.music.musicapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-secrets.properties")
class SpotifySecretsConfigTest {

    @Autowired
    private SpotifySecretsConfig spotifySecretsConfig;

    @Test
    void propertiesAreLoaded() {
        assertNotNull(spotifySecretsConfig.getClientId(), "Client ID should not be null");
        assertNotNull(spotifySecretsConfig.getClientSecret(), "Client Secret should not be null");
        assertNotNull(spotifySecretsConfig.getRedirectUri(), "Redirect URI should not be null");
    }
}
