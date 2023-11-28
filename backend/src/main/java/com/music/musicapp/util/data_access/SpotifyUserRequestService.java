package com.music.musicapp.util.data_access;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class SpotifyUserRequestService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SpotifyTokenService spotifyTokenService;

    public String getTopTracks(int offset, Long user_id) {
        String baseUrl = "https://api.spotify.com/v1/me/top/tracks?time_range=short_term";
        Optional<String> authToken = spotifyTokenService.getAuthorizationToken(user_id);
        if (!authToken.isPresent()) {
            throw new IllegalStateException("No auth token present for user " + user_id);
        }
        String token = authToken.get();

        // Constructing the URL with query parameters
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("offset", offset);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        return response.getBody();
    }
}
