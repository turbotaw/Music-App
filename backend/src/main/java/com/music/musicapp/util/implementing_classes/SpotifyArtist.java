package com.music.musicapp.util.implementing_classes;

import java.util.List;

import com.music.musicapp.util.interfaces.Album;
import com.music.musicapp.util.interfaces.Artist;

public class SpotifyArtist implements Artist {
    private String name;
    private List<String> genres;
    private List<Album> albums;
    private String id;
    private int popularity;
    private String type;
    private String uri;

    public SpotifyArtist(String name, List<String> genres, List<Album> albums, String id, int popularity, String type, String uri) {
        this.name = name;
        this.genres = genres;
        this.albums = albums;
        this.id = id;
        this.popularity = popularity;
        this.type = type;
        this.uri = uri;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getGenres() {
        return genres;
    }

    @Override
    public List<Album> getAlbums() {
        return albums;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}