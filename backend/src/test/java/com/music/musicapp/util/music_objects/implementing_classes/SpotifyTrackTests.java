package com.music.musicapp.util.music_objects.implementing_classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.music.musicapp.util.implementing_classes.SpotifyTrack;
import com.music.musicapp.util.interfaces.Album;
import com.music.musicapp.util.interfaces.Artist;

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

     @Test
    public void testSetTrackName() {
        spotifyTrack.setTrackName("New Track Name");
        assertEquals("New Track Name", spotifyTrack.getTrackName());
    }

    @Test
    public void testSetDurationMs() {
        spotifyTrack.setDurationMs(200000); // 3 minutes and 20 seconds
        assertEquals(200000, spotifyTrack.getDurationMs());
    }

    @Test
    public void testSetIsrcId() {
        spotifyTrack.setIsrcId("new-isrc-456");
        assertEquals("new-isrc-456", spotifyTrack.getIsrcId());
    }

    @Test
    public void testSetUri() {
        spotifyTrack.setUri("https://example.com/new-uri");
        assertEquals("https://example.com/new-uri", spotifyTrack.getUri());
    }

    @Test
    public void testSetPlayable() {
        spotifyTrack.setPlayable(false);
        assertFalse(spotifyTrack.isPlayable());
    }

    @Test
    public void testSetPreviewUrl() {
        spotifyTrack.setPreviewUrl("https://example.com/new-preview");
        assertEquals("https://example.com/new-preview", spotifyTrack.getPreviewUrl());
    }

    @Test
    public void testSetTrackNumber() {
        spotifyTrack.setTrackNumber(5);
        assertEquals(5, spotifyTrack.getTrackNumber());
    }

    @Test
    public void testSetLocal() {
        spotifyTrack.setLocal(true);
        assertTrue(spotifyTrack.isLocal());
    }
}
