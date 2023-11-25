package com.music.musicapp.util.data_access;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.music.musicapp.util.auth.SpotifySecretsConfig;
import com.music.musicapp.util.implementing_classes.SpotifyTokenResponse;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SpotifyTokenService {
    private final RestTemplate restTemplate;
    private final SpotifySecretsConfig spotifySecretsConfig;
    private final SpotifyTokenRepository spotifyTokenRepository;

    @Autowired
    public SpotifyTokenService(SpotifyTokenRepository spotifyTokenRepository, SpotifySecretsConfig spotifySecretsConfig, RestTemplate restTemplate) {
        this.spotifyTokenRepository = spotifyTokenRepository;
        this.spotifySecretsConfig = spotifySecretsConfig;
        this.restTemplate = restTemplate;
    }

    public Optional<SpotifyTokenTable> setAccessToken(Long userId, String token) {
        SpotifyTokenTable tokenTable = spotifyTokenRepository.findById(userId)
                .orElse(new SpotifyTokenTable());

        tokenTable.setUserId(userId);
        tokenTable.setAccessToken(token);
        spotifyTokenRepository.save(tokenTable);

        return Optional.of(tokenTable);
    }

    public Optional<SpotifyTokenTable> setAuthorizationToken(Long userId, String token) {
        SpotifyTokenTable tokenTable = spotifyTokenRepository.findById(userId)
                .orElse(new SpotifyTokenTable());

        tokenTable.setUserId(userId);
        tokenTable.setAuthToken(token);
        spotifyTokenRepository.save(tokenTable);

        return Optional.of(tokenTable);
    }

    public Optional<String> getAuthorizationToken(Long userId) {
        Optional<SpotifyTokenTable> tokenTableOptional = spotifyTokenRepository.findById(userId);
        return tokenTableOptional.map(SpotifyTokenTable::getAuthToken);
    }

    public Optional<String> getAccessToken(Long userId) {
        Optional<SpotifyTokenTable> tokenTableOptional = spotifyTokenRepository.findById(userId);
        return tokenTableOptional.map(SpotifyTokenTable::getAccessToken);
    }

    public SpotifyTokenResponse exchangeCodeForToken(String code, String codeVerifier) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(spotifySecretsConfig.getClientId(), spotifySecretsConfig.getClientSecret());
    
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("code", code);
        map.add("redirect_uri", spotifySecretsConfig.getRedirectUri());
        map.add("code_verifier", codeVerifier);
    
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
    
        try {
            ResponseEntity<String> response = restTemplate.postForEntity("https://accounts.spotify.com/api/token",
                    request, String.class);
    
            if (response.getStatusCode().is2xxSuccessful()) {
                return extractAccessTokenFromResponse(response.getBody());
            } else {
                // Handle non-2xx responses
                throw new RuntimeException("Failed to exchange code for token: " + response.getStatusCode());
            }
        } catch (RestClientException e) {
            // Handle client-side exceptions
            throw new RuntimeException("Error during REST call to exchange code for token", e);
        } catch (Exception e) {
            // Handle other exceptions, such as JSON parsing exceptions
            throw new RuntimeException("Error processing the token exchange response", e);
        }
    }
    public SpotifyTokenResponse extractAccessTokenFromResponse(String responseBody) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(responseBody);

            String accessToken = rootNode.path("access_token").asText();
            String refreshToken = rootNode.path("refresh_token").asText();
            int expiresIn = rootNode.path("expires_in").asInt();

            return new SpotifyTokenResponse(accessToken, refreshToken, expiresIn);

        } catch (Exception e) {
            throw new RuntimeException("Error parsing token response", e);
        }
    }
}