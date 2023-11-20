package com.music.musicapp.util.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-secrets.properties")
class SpotifySecretsConfigTest {

    @Autowired
    private SpotifySecretsConfig spotifySecretsConfig;

    @Test
    void propertiesAreLoaded() {
        assertNotNull(spotifySecretsConfig, "SpotifySecretsConfig should not be null");
        assertNotNull(spotifySecretsConfig.getClientId(), "Client ID should not be null");
        assertNotNull(spotifySecretsConfig.getClientSecret(), "Client Secret should not be null");
        assertNotNull(spotifySecretsConfig.getRedirectUri(), "Redirect URI should not be null");
    }
}
