package com.music.musicapp.util.implementing_classes;

import java.sql.Date;
import java.util.List;

import com.music.musicapp.util.interfaces.Album;
import com.music.musicapp.util.interfaces.Artist;
import com.music.musicapp.util.interfaces.Track;


public class SpotifyAlbum implements Album {
    private String name;
    private int totalTracks;
    private Date releaseDate;
    private List<Artist> artists;
    private String genre;
    private List<Track> tracks;
    private String albumType;
    private List<String> availableMarkets;
    private String href;
    private String id;
    private List<SpotifyImage> images;
    private String releaseDatePrecision;
    private SpotifyRestrictions restrictions;

    public SpotifyAlbum(String name, int totalTracks, Date releaseDate, List<Artist> artists, String genre,
                        List<Track> tracks, String albumType, List<String> availableMarkets, String href, String id,
                        List<SpotifyImage> images, String releaseDatePrecision, SpotifyRestrictions restrictions) {
        this.name = name;
        this.totalTracks = totalTracks;
        this.releaseDate = releaseDate;
        this.artists = artists;
        this.genre = genre;
        this.tracks = tracks;
        this.albumType = albumType;
        this.availableMarkets = availableMarkets;
        this.href = href;
        this.id = id;
        this.images = images;
        this.releaseDatePrecision = releaseDatePrecision;
        this.restrictions = restrictions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalTracks() {
        return totalTracks;
    }

    public void setTotalTracks(int totalTracks) {
        this.totalTracks = totalTracks;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public String getAlbumType() {
        return albumType;
    }

    public void setAlbumType(String albumType) {
        this.albumType = albumType;
    }

    public List<String> getAvailableMarkets() {
        return availableMarkets;
    }

    public void setAvailableMarkets(List<String> availableMarkets) {
        this.availableMarkets = availableMarkets;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SpotifyImage> getImages() {
        return images;
    }

    public void setImages(List<SpotifyImage> images) {
        this.images = images;
    }

    public String getReleaseDatePrecision() {
        return releaseDatePrecision;
    }

    public void setReleaseDatePrecision(String releaseDatePrecision) {
        this.releaseDatePrecision = releaseDatePrecision;
    }

    public SpotifyRestrictions getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(SpotifyRestrictions restrictions) {
        this.restrictions = restrictions;
    }
}