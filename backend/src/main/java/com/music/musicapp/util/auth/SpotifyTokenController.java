package com.music.musicapp.util.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.music.musicapp.util.data_access.SpotifyTokenService;
import com.music.musicapp.util.data_access.SpotifyTokenTable;

@RestController
public class SpotifyTokenController {

    @Autowired
    private SpotifyTokenService spotifyTokenService;

    @GetMapping("/token/auth")
    public Map<String, Object> getAuthorizationToken(@RequestParam Long user_id) {
        Optional<String> token = spotifyTokenService.getAuthorizationToken(user_id);

        Map<String, Object> response = new HashMap<>();
        response.put("user_id", user_id);
        if (token.isPresent()) {
            response.put("auth_token", token.get());
        } else {
            response.put("error", "Authorization token not found for user");
        }

        return response;
    }

    @GetMapping("/token/access")
    public Map<String, Object> getAccessToken(@RequestParam Long user_id) {
        Optional<String> token = spotifyTokenService.getAccessToken(user_id);

        Map<String, Object> response = new HashMap<>();
        response.put("user_id", user_id);
        if (token.isPresent()) {
            response.put("access_token", token.get());
        } else {
            response.put("error", "Access token not found for user");
        }

        return response;
    }

    @PostMapping("/token/auth/set")
public Map<String, Object> setAuthorizationToken(@RequestParam Long user_id, @RequestParam String token) {
    SpotifyTokenTable updatedToken = spotifyTokenService.setAuthorizationToken(user_id, token);

    Map<String, Object> response = new HashMap<>();
    response.put("user_id", user_id);
    if (updatedToken != null) {
        response.put("token", token);
    } else {
        response.put("error", "User not found or update failed");
    }

    return response;
}

@PostMapping("/token/access/set")
public Map<String, Object> setAccessToken(@RequestParam Long user_id, @RequestParam String token) {
    SpotifyTokenTable updatedToken = spotifyTokenService.setAccessToken(user_id, token);

    Map<String, Object> response = new HashMap<>();
    response.put("user_id", user_id);
    if (updatedToken != null) {
        response.put("access_token", token);
    } else {
        response.put("error", "User not found or update failed");
    }

    return response;
}

}
