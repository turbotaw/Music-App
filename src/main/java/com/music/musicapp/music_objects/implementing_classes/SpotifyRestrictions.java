package com.music.musicapp.music_objects.implementing_classes;

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