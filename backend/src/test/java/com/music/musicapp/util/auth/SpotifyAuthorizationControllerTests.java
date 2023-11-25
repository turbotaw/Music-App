package com.music.musicapp.util.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.servlet.view.RedirectView;

import com.music.musicapp.util.data_access.SpotifyTokenService;
import com.music.musicapp.util.data_access.SpotifyTokenTable;

import jakarta.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Optional;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-secrets.properties")
public class SpotifyAuthorizationControllerTests {

    @Mock
    public SpotifySecretsConfig spotifySecretsConfig;

    @Mock
    private SpotifyTokenService spotifyTokenService;

    @Mock
    public HttpSession httpSession;

    @InjectMocks
    public SpotifyAuthorizationController spotifyAuthorizationController;;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(spotifyAuthorizationController.getSpotifySecretsConfig().getClientId()).thenReturn("testClientId");
        when(spotifyAuthorizationController.getSpotifySecretsConfig().getRedirectUri()).thenReturn("testRedirectUri");
    }

    @Test
    public void testSpotifyLogin() {

        // Act
        RedirectView result = spotifyAuthorizationController.spotifyLogin(httpSession);

        // Verify
        verify(spotifyAuthorizationController.getSpotifySecretsConfig()).getClientId();
        verify(spotifyAuthorizationController.getSpotifySecretsConfig()).getRedirectUri();

        // Assert
        assertNotNull(result);
        assertTrue(result.getUrl().contains(
                "https://accounts.spotify.com/authorize?response_type=code&client_id=testClientId&redirect_uri=testRedirectUri&scope=user-read-private user-read-email&code_challenge_method=S256&code_challenge="));
    }

    @Test
    void testSpotifyCallback() {
        // Arrange
        String code = "authorization_code";
        Long userId = 123L;
        SpotifyTokenTable mockTokenTable = new SpotifyTokenTable();

        // Assuming that setAuthorizationToken returns an Optional containing a token
        // table
        when(spotifyTokenService.setAuthorizationToken(userId, code)).thenReturn(Optional.of(mockTokenTable));

        // Act
        String viewName = spotifyAuthorizationController.spotifyCallback(code, userId, httpSession);

        // Verify
        verify(spotifyTokenService).setAuthorizationToken(userId, code);
        assertEquals("redirect:/", viewName);
    }

}