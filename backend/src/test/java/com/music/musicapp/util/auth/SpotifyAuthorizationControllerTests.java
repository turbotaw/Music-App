package com.music.musicapp.util.auth;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.view.RedirectView;

public class SpotifyAuthorizationControllerTests {

    @Mock
    private SpotifySecretsConfig spotifySecretsConfig;

    @InjectMocks
    private SpotifyAuthorizationController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSpotifyLogin() {
        when(spotifySecretsConfig.getClientId()).thenReturn("testClientId");
        when(spotifySecretsConfig.getRedirectUri()).thenReturn("testRedirectUri");

        RedirectView result = controller.spotifyLogin();

        assertNotNull(result);
        String expectedUrl = "https://accounts.spotify.com/authorize?response_type=code&client_id=testClientId&redirect_uri=testRedirectUri&scope=user-read-private user-read-email&code_challenge_method=S256&code_challenge=";
        assertTrue(result.getUrl().startsWith(expectedUrl));
    }

    @Test
    public void testSpotifyCallback() {
        String testCode = "testCode";
        String result = controller.spotifyCallback(testCode);
        assertEquals("redirect:/", result);
    }
}
