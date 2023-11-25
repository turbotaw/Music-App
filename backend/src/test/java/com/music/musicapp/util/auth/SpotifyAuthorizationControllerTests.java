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
import com.music.musicapp.util.implementing_classes.SpotifyTokenResponse;

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
        String codeVerifier = "code_verifier";
        Long userId = 123L;
        SpotifyTokenResponse mockTokenResponse = new SpotifyTokenResponse("access_token", "refresh_token", 3600);
        SpotifyTokenTable mockTokenTable = new SpotifyTokenTable();

        when(httpSession.getAttribute("codeVerifier")).thenReturn(codeVerifier);
        when(spotifyTokenService.exchangeCodeForToken(code, codeVerifier)).thenReturn(mockTokenResponse);
        when(spotifyTokenService.setAuthorizationToken(userId, code)).thenReturn(Optional.of(mockTokenTable));
        when(spotifyTokenService.setAccessToken(userId, mockTokenResponse.getAccessToken())).thenReturn(Optional.of(mockTokenTable));

        // Act
        String viewName = spotifyAuthorizationController.spotifyCallback(code, userId, httpSession);

        // Verify
        verify(httpSession).getAttribute("codeVerifier");
        verify(spotifyTokenService).exchangeCodeForToken(code, codeVerifier);
        verify(spotifyTokenService).setAuthorizationToken(userId, code);
        verify(spotifyTokenService).setAccessToken(userId, mockTokenResponse.getAccessToken());
        assertEquals("redirect:/", viewName);
    }

}