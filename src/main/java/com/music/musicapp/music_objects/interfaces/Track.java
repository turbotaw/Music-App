package com.music.musicapp.music_objects.interfaces;

public interface Track {
    String getTrackName();
    Artist getArtist();
    Album getAlbum();
    int getDurationMs();
    String getIsrcId();
}