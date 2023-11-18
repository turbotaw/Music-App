package com.music.musicapp.util.implementing_classes;

public class SpotifyRestrictions {
    private String reason;

    public SpotifyRestrictions(String reason){
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}