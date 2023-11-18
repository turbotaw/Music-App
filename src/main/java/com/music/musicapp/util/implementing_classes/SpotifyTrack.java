package com.music.musicapp.util.implementing_classes;

import com.music.musicapp.util.interfaces.Album;
import com.music.musicapp.util.interfaces.Artist;
import com.music.musicapp.util.interfaces.Track;

public class SpotifyTrack implements Track {
    private String trackName;
    private Artist artist;
    private Album album;
    private int durationMs;
    private String isrcId;
    private String uri;
    private boolean isPlayable;
    private String previewUrl;
    private int trackNumber;
    private boolean isLocal;

    public SpotifyTrack(String trackName, Artist artist, Album album, int durationMs, String isrcId, String uri,
                        boolean isPlayable, String previewUrl, int trackNumber, boolean isLocal) {
        this.trackName = trackName;
        this.artist = artist;
        this.album = album;
        this.durationMs = durationMs;
        this.isrcId = isrcId;
        this.uri = uri;
        this.isPlayable = isPlayable;
        this.previewUrl = previewUrl;
        this.trackNumber = trackNumber;
        this.isLocal = isLocal;
    }

    @Override
    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    @Override
    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public int getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(int durationMs) {
        this.durationMs = durationMs;
    }

    @Override
    public String getIsrcId() {
        return isrcId;
    }

    public void setIsrcId(String isrcId) {
        this.isrcId = isrcId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public boolean isPlayable() {
        return isPlayable;
    }

    public void setPlayable(boolean playable) {
        isPlayable = playable;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }
}