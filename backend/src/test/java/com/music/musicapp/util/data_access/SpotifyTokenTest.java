package com.music.musicapp.util.data_access;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SpotifyTokenServiceTest {

    @Mock
    private SpotifyTokenRepository spotifyTokenRepository;

    private SpotifyTokenService spotifyTokenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        spotifyTokenService = new SpotifyTokenService(spotifyTokenRepository);
    }

    @Test
    void testSetAccessToken_Found() {
        SpotifyTokenTable mockTokenTable = new SpotifyTokenTable();
        when(spotifyTokenRepository.findById(1L)).thenReturn(Optional.of(mockTokenTable));

        spotifyTokenService.setAccessToken(1L, "newAccessToken");

        verify(spotifyTokenRepository).save(mockTokenTable);
        assertEquals("newAccessToken", mockTokenTable.getAccessToken());
    }

@Test
void testSetAccessToken_NotFound() {
    when(spotifyTokenRepository.findById(1L)).thenReturn(Optional.empty());

    Optional<SpotifyTokenTable> result = spotifyTokenService.setAccessToken(1L, "newAccessToken");

    assertTrue(result.isEmpty());
    verify(spotifyTokenRepository, never()).save(any());
}

    @Test
    void testSetAuthorizationToken_Found() {
        SpotifyTokenTable mockTokenTable = new SpotifyTokenTable();
        when(spotifyTokenRepository.findById(1L)).thenReturn(Optional.of(mockTokenTable));

        spotifyTokenService.setAuthorizationToken(1L, "newAuthToken");

        verify(spotifyTokenRepository).save(mockTokenTable);
        assertEquals("newAuthToken", mockTokenTable.getAuthToken());
    }

@Test
void testSetAuthorizationToken_NotFound() {
    when(spotifyTokenRepository.findById(1L)).thenReturn(Optional.empty());

    Optional<SpotifyTokenTable> result = spotifyTokenService.setAuthorizationToken(1L, "newAuthToken");

    assertTrue(result.isEmpty());
    verify(spotifyTokenRepository, never()).save(any());
}

    @Test
    void testGetAuthorizationToken_Found() {
        SpotifyTokenTable mockTokenTable = new SpotifyTokenTable();
        mockTokenTable.setAuthToken("authTokenValue");
        when(spotifyTokenRepository.findById(1L)).thenReturn(Optional.of(mockTokenTable));

        Optional<String> result = spotifyTokenService.getAuthorizationToken(1L);

        assertTrue(result.isPresent());
        assertEquals("authTokenValue", result.get());
    }

@Test
void testGetAuthorizationToken_NotFound() {
    when(spotifyTokenRepository.findById(1L)).thenReturn(Optional.empty());

    Optional<String> result = spotifyTokenService.getAuthorizationToken(1L);

    assertTrue(result.isEmpty());
}

    @Test
    void testGetAccessToken_Found() {
        SpotifyTokenTable mockTokenTable = new SpotifyTokenTable();
        mockTokenTable.setAccessToken("accessTokenValue");
        when(spotifyTokenRepository.findById(1L)).thenReturn(Optional.of(mockTokenTable));

        Optional<String> result = spotifyTokenService.getAccessToken(1L);

        assertTrue(result.isPresent());
        assertEquals("accessTokenValue", result.get());
    }

@Test
void testGetAccessToken_NotFound() {
    when(spotifyTokenRepository.findById(1L)).thenReturn(Optional.empty());

    Optional<String> result = spotifyTokenService.getAccessToken(1L);

    assertTrue(result.isEmpty());
}

}
