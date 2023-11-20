package com.music.musicapp.util.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class SpotifyTokenControllerTests {

    @Autowired
    private SpotifyTokenController spotifyTokenController;

    @MockBean
    private SpotifyTokenService spotifyTokenService;

    @BeforeEach
    void setUp() {
        // Configure behavior of the mock here if needed
    }

    @Test
    void testGetAuthorizationToken() {
        when(spotifyTokenService.getAuthorizationToken()).thenReturn("test");
        String result = spotifyTokenController.getAuthorizationToken();

        assertEquals("test", result);
        verify(spotifyTokenService).setAccessToken("test");
        verify(spotifyTokenService).setAuthorizationToken("test");
    }

    @Test
    void testGetAccessToken() {
        String auth_token = "test_token";
        when(spotifyTokenService.getAccessToken(auth_token)).thenReturn("access_token");
        String result = spotifyTokenController.getAccessToken(auth_token);

        assertEquals("access_token", result);
    }
}
