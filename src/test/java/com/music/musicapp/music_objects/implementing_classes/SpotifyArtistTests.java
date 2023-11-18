package com.music.musicapp.music_objects.implementing_classes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.music.musicapp.util.implementing_classes.SpotifyArtist;
import com.music.musicapp.util.interfaces.Album;

public class SpotifyArtistTests {

    private SpotifyArtist spotifyArtist;

    @BeforeEach
    public void setUp() {

        Album album1 = mock(Album.class);
        Album album2 = mock(Album.class);

        List<Album> albums = new ArrayList<>();
        albums.add(album1);
        albums.add(album2);

        spotifyArtist = new SpotifyArtist(
            "Test Artist",
            List.of("Rock", "Pop"),
            albums,
            "artist-123",
            100,
            "artist",
            "https://example.com/artist"
        );
    }

    @Test
    public void testSetName() {
        spotifyArtist.setName("Artist Name");
        assertEquals("Artist Name", spotifyArtist.getName());
    }

    @Test
    public void testSetGenres() {
        List<String> genres = new ArrayList<>();
        genres.add("Pop");
        genres.add("Rock");
        spotifyArtist.setGenres(genres);
        assertEquals(genres, spotifyArtist.getGenres());
    }

    @Test
    public void testSetAlbums() {
        List<Album> albums = new ArrayList<>();
        Album album1 = mock(Album.class);
        Album album2 = mock(Album.class);
        albums.add(album1);
        albums.add(album2);

        spotifyArtist.setAlbums(albums);
        assertEquals(albums, spotifyArtist.getAlbums());
    }

    @Test
    public void testSetId() {
        spotifyArtist.setId("artist123");
        assertEquals("artist123", spotifyArtist.getId());
    }

    @Test
    public void testSetPopularity() {
        spotifyArtist.setPopularity(75);
        assertEquals(75, spotifyArtist.getPopularity());
    }

    @Test
    public void testSetType() {
        spotifyArtist.setType("artist");
        assertEquals("artist", spotifyArtist.getType());
    }

    @Test
    public void testSetUri() {
        spotifyArtist.setUri("spotify:artist:artist123");
        assertEquals("spotify:artist:artist123", spotifyArtist.getUri());
    }
}

