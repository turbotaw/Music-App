package com.music.musicapp.music_objects.interfaces;

import java.util.List;

public interface Artist {
    String getName();
    List<String> getGenres();
    List<Album> getAlbums();
}
