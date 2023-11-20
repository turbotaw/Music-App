package com.music.musicapp.util.data_access;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SpotifyTokenServiceTests {

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
        Long userId = 123L;
        SpotifyTokenTable mockTokenTable = new SpotifyTokenTable();
        mockTokenTable.setAccessToken("testAccessToken");
        when(spotifyTokenRepository.findById(userId)).thenReturn(Optional.of(mockTokenTable));

        Optional<String> result = spotifyTokenService.getAccessToken(123L);

        assertTrue(result.isPresent());
        assertEquals("testAccessToken", result.get());
    }

    @Test
    void testSetAccessToken() {
        String code = "authorization_code";
        Long userId = 123L;
        SpotifyTokenTable mockTokenTable = new SpotifyTokenTable();
        when(spotifyTokenRepository.findById(userId)).thenReturn(Optional.of(mockTokenTable));

        spotifyTokenService.setAccessToken(userId, code);

        verify(spotifyTokenRepository).save(mockTokenTable);
        assertEquals(code, mockTokenTable.getAccessToken());
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
        String code = "authorization_token";
        SpotifyTokenTable mockTokenTable = new SpotifyTokenTable();
        mockTokenTable.setAuthToken(code);

        when(spotifyTokenRepository.findById(123L)).thenReturn(Optional.of(mockTokenTable));
        spotifyTokenService.setAuthorizationToken(123L, code);

        Optional<String> result = spotifyTokenService.getAuthorizationToken(123L);

        assertTrue(result.isPresent());
        assertEquals(code, result.get());
        verify(spotifyTokenRepository).save(mockTokenTable);
    }
}
