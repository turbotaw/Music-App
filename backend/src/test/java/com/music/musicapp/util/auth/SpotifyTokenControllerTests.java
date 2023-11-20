package com.music.musicapp.util.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.music.musicapp.util.data_access.SpotifyTokenService;

@SpringBootTest
public class SpotifyTokenControllerTests {

    @Autowired
    private SpotifyTokenController spotifyTokenController;

    @MockBean
    private SpotifyTokenService spotifyTokenService;

    @Test
    void testGetAuthorizationToken() {
        Long userId = 123L;
        String expectedToken = "test";
        when(spotifyTokenService.getAuthorizationToken(userId)).thenReturn(Optional.of(expectedToken));
        Map<String, Object> result = spotifyTokenController.getAuthorizationToken(userId);

        assertEquals(userId, result.get("user_id"));
        assertEquals(expectedToken, result.get("auth_token"));
        verify(spotifyTokenService).getAuthorizationToken(userId);
    }

    @Test
    void testGetAccessToken() {
        Long userId = 123L;
        String expectedAccessToken = "test_token";
        when(spotifyTokenService.getAccessToken(userId)).thenReturn(Optional.of(expectedAccessToken));
        Map<String, Object> result = spotifyTokenController.getAccessToken(userId);

        assertEquals(userId, result.get("user_id"));
        assertEquals(expectedAccessToken, result.get("access_token"));
        verify(spotifyTokenService).getAccessToken(userId);
    }
}
