package com.music.musicapp.util.implementing_classes;

public class SpotifyTokenResponse {
        private String accessToken;
        private String refreshToken;
        private int expiresIn;
    
        public SpotifyTokenResponse(String accessToken, String refreshToken, int expiresIn) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
            this.expiresIn = expiresIn;
        }
    
       public String getAccessToken(){
        return accessToken;
       }

       public String getRefreshToken(){
        return refreshToken;
       }

       public int getExpiresIn(){
        return expiresIn;
       }

       public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
       }

       public void setRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
       }

       public void setExpiresIn(int expiresIn){
        this.expiresIn = expiresIn;
       }
}
