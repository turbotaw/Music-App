package com.music.musicapp.util.auth;

import org.springframework.stereotype.Service;

import com.music.musicapp.util.interfaces.TokenService;

@Service
public class SpotifyTokenService implements TokenService{

    private String accessToken;
    private String authToken;
   @Override
    public String getAccessToken(String code){
            return accessToken;
        
    }

    public String setAccessToken(String code){
        accessToken = code;
        return accessToken;
    }
    @Override
    public String getAuthorizationToken(){
        return authToken;
    }

    public void setAuthorizationToken(String code){
        authToken = code;
    }
}