package com.music.musicapp.music_objects.implementing_classes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.music.musicapp.music_objects.interfaces.Artist;
import com.music.musicapp.music_objects.interfaces.Track;

public class SpotifyAlbumTests {

    private SpotifyAlbum spotifyAlbum;

    @BeforeEach
    public void setUp() {
        
        Artist artist1 = mock(Artist.class);
        Artist artist2 = mock(Artist.class);

        Track track1 = mock(Track.class);
        Track track2 = mock(Track.class);

        List<Artist> artists = List.of(artist1, artist2);
        List<Track> tracks = List.of(track1, track2);

        spotifyAlbum = new SpotifyAlbum(
            "Test Album",
            10,
            Date.valueOf("2022-01-01"),
            artists,
            "Rock",
            tracks,
            "album",
            List.of("US", "CA"),
            "https://example.com/album",
            "album-123",
            new ArrayList<>(),
            "day",
            new SpotifyRestrictions("market")
        );
    }


    @Test
    public void testSetName() {
        spotifyAlbum.setName("Album Name");
        assertEquals("Album Name", spotifyAlbum.getName());
    }

    @Test
    public void testSetTotalTracks() {
        spotifyAlbum.setTotalTracks(10);
        assertEquals(10, spotifyAlbum.getTotalTracks());
    }

    @Test
    public void testSetReleaseDate() {
        Date releaseDate = new Date(System.currentTimeMillis());
        spotifyAlbum.setReleaseDate(releaseDate);
        assertEquals(releaseDate, spotifyAlbum.getReleaseDate());
    }

    @Test
    public void testSetArtists() {
        List<Artist> artists = new ArrayList<>();
        Artist artist1 = mock(Artist.class);
        Artist artist2 = mock(Artist.class);
        artists.add(artist1);
        artists.add(artist2);

        spotifyAlbum.setArtists(artists);
        assertEquals(artists, spotifyAlbum.getArtists());
    }

    @Test
    public void testSetGenre() {
        spotifyAlbum.setGenre("Rock");
        assertEquals("Rock", spotifyAlbum.getGenre());
    }

    @Test
    public void testSetTracks() {
        List<Track> tracks = new ArrayList<>();
        Track track1 = mock(Track.class);
        Track track2 = mock(Track.class);
        tracks.add(track1);
        tracks.add(track2);

        spotifyAlbum.setTracks(tracks);
        assertEquals(tracks, spotifyAlbum.getTracks());
    }

    @Test
    public void testSetAlbumType() {
        spotifyAlbum.setAlbumType("Compilation");
        assertEquals("Compilation", spotifyAlbum.getAlbumType());
    }

    @Test
    public void testSetRestrictions() {
        SpotifyRestrictions restrictions = mock(SpotifyRestrictions.class);
        spotifyAlbum.setRestrictions(restrictions);
        assertEquals(restrictions, spotifyAlbum.getRestrictions());
    }
}
