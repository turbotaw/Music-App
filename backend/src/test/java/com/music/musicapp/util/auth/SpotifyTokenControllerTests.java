package com.music.musicapp.util.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.util.Optional;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.music.musicapp.util.data_access.SpotifyTokenService;
import com.music.musicapp.util.data_access.SpotifyTokenTable;

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
    void testGetAuthorizationTokenFailed() {
        Long userId = 123L;
        when(spotifyTokenService.getAuthorizationToken(userId)).thenReturn(Optional.empty());
        Map<String, Object> result = spotifyTokenController.getAuthorizationToken(userId);

        assertEquals(userId, result.get("user_id"));
        assertNull(result.get("auth_token"));
        assertNotNull(result.get("error"));
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

    @Test
    void testGetAccessTokenFailed() {
        Long userId2 = 456L;
        when(spotifyTokenService.getAccessToken(userId2)).thenReturn(Optional.empty());
        Map<String, Object> result2 = spotifyTokenController.getAccessToken(userId2);

        assertEquals(userId2, result2.get("user_id"));
        assertNull(result2.get("access_token"));
        assertNotNull(result2.get("error"));
        verify(spotifyTokenService).getAccessToken(userId2);
    }

    @Test
    void testSetAuthorizationToken() {
        // Arrange
        Long userId = 123L;
        String token = "authTokenValue";
        SpotifyTokenTable updatedToken = new SpotifyTokenTable();
        when(spotifyTokenService.setAuthorizationToken(userId, token)).thenReturn(updatedToken);

        // Act
        Map<String, Object> response = spotifyTokenController.setAuthorizationToken(userId, token);

        // Assert
        assertNotNull(response);
        assertEquals(userId, response.get("user_id"));
        assertEquals(token, response.get("token"));
    }

    @Test
    void testSetAuthorizationTokenUpdateFailed() {
        // Arrange
        Long userId = 123L;
        String token = "authTokenValue";
        SpotifyTokenTable updatedToken = null; // Update failed
        when(spotifyTokenService.setAuthorizationToken(userId, token)).thenReturn(updatedToken);

        // Act
        Map<String, Object> response = spotifyTokenController.setAuthorizationToken(userId, token);

        // Assert
        assertNotNull(response);
        assertEquals(userId, response.get("user_id"));
        assertEquals("User not found or update failed", response.get("error"));
    }

    @Test
    void testSetAccessToken() {
        // Arrange
        Long userId = 123L;
        String token = "accessTokenValue";
        SpotifyTokenTable updatedToken = new SpotifyTokenTable();
        when(spotifyTokenService.setAccessToken(userId, token)).thenReturn(updatedToken);

        // Act
        Map<String, Object> response = spotifyTokenController.setAccessToken(userId, token);

        // Assert
        assertNotNull(response);
        assertEquals(userId, response.get("user_id"));
        assertEquals(token, response.get("access_token"));
    }

    @Test
    void testSetAccessTokenUpdateFailed() {
        // Arrange
        Long userId = 123L;
        String token = "accessTokenValue";
        SpotifyTokenTable updatedToken = null; // Update failed
        when(spotifyTokenService.setAccessToken(userId, token)).thenReturn(updatedToken);

        // Act
        Map<String, Object> response = spotifyTokenController.setAccessToken(userId, token);

        // Assert
        assertNotNull(response);
        assertEquals(userId, response.get("user_id"));
        assertEquals("User not found or update failed", response.get("error"));
    }
}
