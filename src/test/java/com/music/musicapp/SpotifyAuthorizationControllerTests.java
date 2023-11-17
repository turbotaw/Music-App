package com.music.musicapp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.servlet.view.RedirectView;

class SpotifyAuthorizationControllerTests {

    @Mock
    private SpotifySecretsConfig spotifySecretsConfig;

    @InjectMocks
    private SpotifyAuthorizationController controller;

    @BeforeEach
    void setUp() {
        spotifySecretsConfig = mock(SpotifySecretsConfig.class);
        controller = new SpotifyAuthorizationController(spotifySecretsConfig);
    }

    @Test
    void testSpotifyLogin() {
        String clientId = "test-client-id";
        String redirectUri = "http://example.com/callback";

        when(spotifySecretsConfig.getClientId()).thenReturn(clientId);
        when(spotifySecretsConfig.getRedirectUri()).thenReturn(redirectUri);

        RedirectView result = controller.spotifyLogin();

        assertNotNull(result);
        String url = result.getUrl();
        assertNotNull(url);
        assertTrue(url.startsWith("https://accounts.spotify.com/authorize"));
        assertTrue(url.contains("response_type=code"));
        assertTrue(url.contains("client_id=" + clientId));
        assertTrue(url.contains("redirect_uri=" + redirectUri));
        assertTrue(url.contains("code_challenge_method=S256"));
        assertTrue(url.contains("code_challenge=")); 
    }

    @Test
    void testSpotifyCallback() {
        String code = "test-code";
        String redirectUrl = controller.spotifyCallback(code);
        assertEquals("redirect:/", redirectUrl);
    }
}
