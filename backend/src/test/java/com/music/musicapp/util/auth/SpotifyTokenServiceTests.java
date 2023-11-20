package com.music.musicapp.util.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SpotifyTokenServiceTests {

    private SpotifyTokenService spotifyTokenService;

    @BeforeEach
    void setUp() {
        spotifyTokenService = new SpotifyTokenService();
    }

    @Test
    void testGetAccessToken() {
        String code = "authorization_code";
        String accessToken = spotifyTokenService.getAccessToken(code);

        assertEquals(null, accessToken); 
    }

    @Test
    void testSetAccessToken() {
        String code = "authorization_code";
        String accessToken = spotifyTokenService.setAccessToken(code);

        assertEquals(code, accessToken);
    }

    @Test
    void testGetAuthorizationToken() {
        String authToken = spotifyTokenService.getAuthorizationToken();

        assertEquals(null, authToken);
    }

    @Test
    void testSetAuthorizationToken() {
        String code = "authorization_token";
        spotifyTokenService.setAuthorizationToken(code);

        assertEquals(code, spotifyTokenService.getAuthorizationToken());
    }
}
