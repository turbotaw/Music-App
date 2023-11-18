package com.music.musicapp.util.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpotifyTokenController {

    @Autowired
    private SpotifyTokenService spotifyTokenService;

    @RequestMapping("/token")
    public String getAuthorizationToken(){
        spotifyTokenService.setAccessToken("test");
        spotifyTokenService.setAuthorizationToken("test");
        return spotifyTokenService.getAuthorizationToken();
    }

    @RequestMapping("/token/{auth_token}")
    public String getAccessToken(@PathVariable String auth_token){
        return spotifyTokenService.getAccessToken(auth_token);
    }

}
