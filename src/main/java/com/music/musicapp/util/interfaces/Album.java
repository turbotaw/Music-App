package com.music.musicapp.util.interfaces;

import java.util.Date;
import java.util.List;

public interface Album {
    String getName();
    int getTotalTracks();
    Date getReleaseDate();
    List<Artist> getArtists();
    String getGenre();
    List<Track> getTracks();
}
