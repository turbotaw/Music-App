package com.music.musicapp.music_objects.interfaces;

import java.util.Date;
import java.util.List;
// import com.music.musicapp.music_objects.interfaces.Artist;
// import com.music.musicapp.music_objects.interfaces.Track;

public interface Album {
    String getName();
    int getTotalTracks();
    Date getReleaseDate();
    List<Artist> getArtists();
    String getGenre();
    List<Track> getTracks();
}
