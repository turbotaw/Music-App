package com.music.musicapp.util.interfaces;

public interface TokenService {
    String getAuthorizationToken(Long user_id);
    String getAccessToken(Long user_id);
}
