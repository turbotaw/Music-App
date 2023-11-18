package com.music.musicapp.music_objects.implementing_classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.music.musicapp.music_objects.interfaces.Album;
import com.music.musicapp.music_objects.interfaces.Artist;

public class SpotifyTrackTests {

    private SpotifyTrack spotifyTrack;
    private Artist artist;
    private Album album;

    @BeforeEach
    public void setUp() {
        artist = mock(Artist.class);
        album = mock(Album.class);

        spotifyTrack = new SpotifyTrack(
            "Test Track",
            artist,
            album,
            180000, // 3 minutes
            "isrc-123",
            "https://example.com/track",
            true,
            "https://example.com/track/preview",
            1,
            false
        );
    }

    @Test
    public void testGetTrackName() {
        assertEquals("Test Track", spotifyTrack.getTrackName());
    }

    @Test
    public void testGetArtist() {
        assertEquals(artist, spotifyTrack.getArtist());
    }

    @Test
    public void testGetAlbum() {
        assertEquals(album, spotifyTrack.getAlbum());
    }

    @Test
    public void testGetDurationMs() {
        assertEquals(180000, spotifyTrack.getDurationMs());
    }

    @Test
    public void testGetIsrcId() {
        assertEquals("isrc-123", spotifyTrack.getIsrcId());
    }

    @Test
    public void testGetUri() {
        assertEquals("https://example.com/track", spotifyTrack.getUri());
    }

    @Test
    public void testIsPlayable() {
        assertEquals(true, spotifyTrack.isPlayable());
    }

    @Test
    public void testGetPreviewUrl() {
        assertEquals("https://example.com/track/preview", spotifyTrack.getPreviewUrl());
    }

    @Test
    public void testGetTrackNumber() {
        assertEquals(1, spotifyTrack.getTrackNumber());
    }

    @Test
    public void testIsLocal() {
        assertEquals(false, spotifyTrack.isLocal());
    }
}
