package com.music.musicapp.util.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.SecureRandom;
import java.util.Base64;
import java.security.MessageDigest;

@Controller
public class SpotifyAuthorizationController {

    private final SpotifySecretsConfig spotifySecretsConfig;
    private String codeVerifier;

    @Autowired
    public SpotifyAuthorizationController(SpotifySecretsConfig spotifySecretsConfig) {
        this.spotifySecretsConfig = spotifySecretsConfig;
    }


    @GetMapping("/login/spotify")
    public RedirectView spotifyLogin() {
        String clientId = spotifySecretsConfig.getClientId();
        String redirectUri = spotifySecretsConfig.getRedirectUri();

        codeVerifier = generateCodeVerifier();
        String codeChallenge = generateCodeChallenge(codeVerifier);

        String authorizationUri = "https://accounts.spotify.com/authorize" +
                "?response_type=code" +
                "&client_id=" + clientId +
                "&redirect_uri=" + redirectUri +
                "&scope=user-read-private user-read-email" +
                "&code_challenge_method=S256" +
                "&code_challenge=" + codeChallenge;

        return new RedirectView(authorizationUri);
    }

    @GetMapping("/callback")
    public String spotifyCallback(@RequestParam("code") String code) {
        //TODO: need to properly store the auth token
        return "redirect:/";
    }

    private String generateCodeVerifier() {
        SecureRandom sr = new SecureRandom();
        byte[] code = new byte[32];
        sr.nextBytes(code);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(code);
    }

    private String generateCodeChallenge(String verifier) {
        try {
            byte[] bytes = verifier.getBytes("US-ASCII");
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(bytes, 0, bytes.length);
            byte[] digest = md.digest();
            return Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
        } catch (Exception e) {
            throw new IllegalStateException("Error creating code challenge", e);
        }
    }
}
