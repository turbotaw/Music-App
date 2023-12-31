package com.music.musicapp.util.data_access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "spotifytokentable", schema = "public")
public class SpotifyTokenTable {

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "access_token", length = 400)
    private String accessToken;

    @Column(name = "auth_token", length = 400)
    private String authToken;

    @Column(name = "refresh_token", length = 400)
    private String refreshToken;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    // public AppUser getAppUser() {
    //     return appUser;
    // }

    // public void setAppUser(AppUser appUser) {
    //     this.appUser = appUser;
    // }
}