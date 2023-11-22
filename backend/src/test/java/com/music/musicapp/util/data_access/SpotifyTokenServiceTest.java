package com.music.musicapp.util.data_access;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SpotifyTokenServiceTest {

    @Mock
    private SpotifyTokenRepository spotifyTokenRepository;

    @InjectMocks
    private SpotifyTokenService spotifyTokenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAccessToken() {
        // Arrange
        Long userId = 123L;
        SpotifyTokenTable mockTokenTable = new SpotifyTokenTable();
        mockTokenTable.setAccessToken("testAccessToken");

        // Mock the behavior of the repository
        when(spotifyTokenRepository.findById(userId)).thenReturn(Optional.of(mockTokenTable));

        // Act
        Optional<String> result = spotifyTokenService.getAccessToken(123L);

        // Verify 
        assertTrue(result.isPresent());
        assertEquals("testAccessToken", result.get());
    }

    @Test
    void testSetAccessToken() {
        // Arrange
        Long userId = 123L;
        String token = "testAccessToken";
        SpotifyTokenTable mockTokenTable = new SpotifyTokenTable();

        // Mock the behavior of the repository
        when(spotifyTokenRepository.findById(userId)).thenReturn(Optional.of(mockTokenTable));
        when(spotifyTokenRepository.save(any(SpotifyTokenTable.class))).thenReturn(mockTokenTable);

        // Act
        Optional<SpotifyTokenTable> result = spotifyTokenService.setAccessToken(userId, token);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getUserId());
        assertEquals(token, result.get().getAccessToken());

        // Verify that findById and save methods were called
        verify(spotifyTokenRepository).findById(userId);
        verify(spotifyTokenRepository).save(mockTokenTable);
    }

    @Test
    void testGetAuthorizationToken() {
        Long userId = 123L;
        SpotifyTokenTable mockTokenTable = new SpotifyTokenTable();
        mockTokenTable.setAuthToken("authTokenValue");
        when(spotifyTokenRepository.findById(userId)).thenReturn(Optional.of(mockTokenTable));

        Optional<String> authToken = spotifyTokenService.getAuthorizationToken(123L);

        assertTrue(authToken.isPresent());
        assertEquals("authTokenValue", authToken.get());
    }

    @Test
    void testSetAuthorizationToken() {
        // Arrange
        String code = "authorization_token";
        SpotifyTokenTable mockTokenTable = new SpotifyTokenTable();
        mockTokenTable.setAuthToken(code);

        // Mock the behavior of the repository
        when(spotifyTokenRepository.findById(123L)).thenReturn(Optional.of(mockTokenTable));
        spotifyTokenService.setAuthorizationToken(123L, code);

        // Act
        Optional<String> result = spotifyTokenService.getAuthorizationToken(123L);

        // Verify 
        assertTrue(result.isPresent());
        assertEquals(code, result.get());
        verify(spotifyTokenRepository).save(mockTokenTable);
    }

    @Test
    void testOverwriteAccessToken() {
        // Arrange
        Long userId = 123L;
        String initialToken = "initialAccessToken";
        String newToken = "newAccessToken";
        SpotifyTokenTable existingTokenTable = new SpotifyTokenTable();
        existingTokenTable.setUserId(userId);
        existingTokenTable.setAccessToken(initialToken);

        // Mock the behavior of the repository
        when(spotifyTokenRepository.findById(userId)).thenReturn(Optional.of(existingTokenTable));
        when(spotifyTokenRepository.save(any(SpotifyTokenTable.class))).thenReturn(existingTokenTable);

        // Act
        Optional<SpotifyTokenTable> result = spotifyTokenService.setAccessToken(userId, newToken);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getUserId());
        assertEquals(newToken, result.get().getAccessToken());

        // Verify that findById and save methods were called
        verify(spotifyTokenRepository).findById(userId);
        verify(spotifyTokenRepository).save(existingTokenTable);
    }

    @Test
void testOverwriteAuthorizationToken() {
    // Arrange
    String initialToken = "initialAuthToken";
    String newToken = "newAuthToken";
    SpotifyTokenTable existingTokenTable = new SpotifyTokenTable();
    existingTokenTable.setUserId(123L);
    existingTokenTable.setAuthToken(initialToken);

    // Mock the behavior of the repository
    when(spotifyTokenRepository.findById(123L)).thenReturn(Optional.of(existingTokenTable));
    spotifyTokenService.setAuthorizationToken(123L, newToken);

    // Act
    Optional<String> result = spotifyTokenService.getAuthorizationToken(123L);

    // Verify
    assertTrue(result.isPresent());
    assertEquals(newToken, result.get());
    verify(spotifyTokenRepository).save(existingTokenTable);
}

}
