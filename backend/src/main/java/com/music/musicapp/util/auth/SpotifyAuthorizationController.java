package com.music.musicapp.util.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class SpotifyAuthorizationController {

    private final SpotifySecretsConfig spotifySecretsConfig;
    private String codeVerifier;
    
    public SpotifyAuthorizationController(SpotifySecretsConfig spotifySecretsConfig) {
        this.spotifySecretsConfig = spotifySecretsConfig;
    }


    @GetMapping("/login/spotify")
    public RedirectView spotifyLogin() {
        String clientId = spotifySecretsConfig.getClientId();
        String redirectUri = spotifySecretsConfig.getRedirectUri();

        codeVerifier = CodeGenerator.generateCodeVerifier();
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
    public String spotifyCallback(@RequestParam("code") String code) {
        //TODO: need to properly store the auth token
        return "redirect:/";
    }
}
