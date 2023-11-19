package com.music.musicapp.util.interfaces;

public interface Track {
    String getTrackName();
    Artist getArtist();
    Album getAlbum();
    int getDurationMs();
    String getIsrcId();
}