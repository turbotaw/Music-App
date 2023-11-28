package com.music.musicapp.util.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.music.musicapp.util.data_access.SpotifyTokenService;
import com.music.musicapp.util.implementing_classes.SpotifyTokenResponse;

import jakarta.servlet.http.HttpSession;

@Controller
public class SpotifyAuthorizationController {

    private final SpotifySecretsConfig spotifySecretsConfig;
    private String codeVerifier;

    @Autowired
    SpotifyTokenService spotifyTokenService;

    public SpotifyAuthorizationController(SpotifySecretsConfig spotifySecretsConfig) {
        this.spotifySecretsConfig = spotifySecretsConfig;
    }

    public SpotifySecretsConfig getSpotifySecretsConfig() {
        return spotifySecretsConfig;
    }

    @PostMapping("/login/spotify")
    public RedirectView spotifyLogin(HttpSession session) {
        String clientId = spotifySecretsConfig.getClientId();
        String redirectUri = spotifySecretsConfig.getRedirectUri();

        codeVerifier = CodeGenerator.generateCodeVerifier();
        session.setAttribute("codeVerifier", codeVerifier);
        String codeChallenge = CodeGenerator.generateCodeChallenge(codeVerifier);

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
    public String spotifyCallback(@RequestParam("code") String code, @RequestParam("user_id") Long user_id,
            HttpSession session) {
        String codeVerifier = (String) session.getAttribute("codeVerifier");
        SpotifyTokenResponse accessToken = spotifyTokenService.exchangeCodeForToken(code, codeVerifier);

        spotifyTokenService.setAuthorizationToken(user_id, code);
        spotifyTokenService.setAccessToken(user_id, accessToken.getAccessToken());
        return "redirect:/";
    }

}
