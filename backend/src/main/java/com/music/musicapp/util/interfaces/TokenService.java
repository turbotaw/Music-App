package com.music.musicapp.util.interfaces;

public interface TokenService {
    String getAuthorizationToken();
    String getAccessToken(String code);
}
