package com.music.musicapp.util.interfaces;

import java.util.List;

public interface Artist {
    String getName();
    List<String> getGenres();
    List<Album> getAlbums();
}
