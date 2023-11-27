package com.music.musicapp.util.data_access;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class SpotifyUserRequestServiceTests {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private SpotifyTokenService spotifyTokenService;

    @InjectMocks
    private SpotifyUserRequestService spotifyUserRequestService;

    @Test
    void getTopTracks_WhenTokenPresent_ShouldReturnTracks() {
        String expectedResponse = "some JSON string";
        when(spotifyTokenService.getAuthorizationToken(anyLong())).thenReturn(Optional.of("validToken"));
        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                any(HttpEntity.class),
                eq(String.class))
        ).thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        String actualResponse = spotifyUserRequestService.getTopTracks(0, 1L);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getTopTracks_WhenTokenNotPresent_ShouldThrowException() {
        when(spotifyTokenService.getAuthorizationToken(anyLong())).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () -> spotifyUserRequestService.getTopTracks(0, 1L));
    }
}