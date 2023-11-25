package com.music.musicapp.util.implementing_classes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SpotifyTokenResponseTests {
    @Test
    public void testAccessTokenGetterAndSetter() {
        String testAccessToken = "accessTestToken";
        SpotifyTokenResponse SpotifytokenResponse = new SpotifyTokenResponse(null, null, 0);
        
        SpotifytokenResponse.setAccessToken(testAccessToken);
        assertEquals(testAccessToken, SpotifytokenResponse.getAccessToken(), "Access token getter or setter not working correctly");
    }

    @Test
    public void testRefreshTokenGetterAndSetter() {
        String testRefreshToken = "refreshTestToken";
        SpotifyTokenResponse SpotifytokenResponse = new SpotifyTokenResponse(null, null, 0);
        
        SpotifytokenResponse.setRefreshToken(testRefreshToken);
        assertEquals(testRefreshToken, SpotifytokenResponse.getRefreshToken(), "Refresh token getter or setter not working correctly");
    }

    @Test
    public void testExpiresInGetterAndSetter() {
        int testExpiresIn = 3600;
        SpotifyTokenResponse SpotifytokenResponse = new SpotifyTokenResponse(null, null, 0);
        
        SpotifytokenResponse.setExpiresIn(testExpiresIn);
        assertEquals(testExpiresIn, SpotifytokenResponse.getExpiresIn(), "ExpiresIn getter or setter not working correctly");
    }
}
