package com.music.musicapp.util.music_objects.implementing_classes;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.music.musicapp.util.implementing_classes.SpotifyAlbum;
import com.music.musicapp.util.implementing_classes.SpotifyArtist;
import com.music.musicapp.util.implementing_classes.SpotifyImage;
import com.music.musicapp.util.implementing_classes.SpotifyRestrictions;
import com.music.musicapp.util.implementing_classes.SpotifyTrack;
import com.music.musicapp.util.interfaces.Album;
import com.music.musicapp.util.interfaces.Artist;
import com.music.musicapp.util.interfaces.Track;

public class SpotifyAlbumTests {

    private SpotifyAlbum spotifyAlbum;

    @BeforeEach
    public void setUp() {
        List<String> genres = new ArrayList<>();
        genres.add("Pop");
        genres.add("Rock");

        List<Album> albums = new ArrayList<>();
        SpotifyAlbum album1 = Mockito.mock(SpotifyAlbum.class);

        SpotifyAlbum album2 = Mockito.mock(SpotifyAlbum.class);

        albums.add(album1);
        albums.add(album2);

        SpotifyArtist spotifyArtist = Mockito.mock(SpotifyArtist.class);
        SpotifyTrack spotifyTrack = Mockito.mock(SpotifyTrack.class);

        List<Artist> artists = List.of(spotifyArtist);
        List<Track> tracks = List.of(spotifyTrack);

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
                new SpotifyRestrictions("market"));
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
        ArrayList<Artist> artistList = new ArrayList<>();
        SpotifyArtist artist1 = Mockito.mock(SpotifyArtist.class);
        SpotifyArtist artist2 = Mockito.mock(SpotifyArtist.class);
        artistList.add(artist1);
        artistList.add(artist2);

        spotifyAlbum.setArtists(artistList);

        assertEquals(artistList, spotifyAlbum.getArtists());
    }

    @Test
    public void testSetGenre() {
        spotifyAlbum.setGenre("Pop");
        assertEquals("Pop", spotifyAlbum.getGenre());
    }

    @Test
    public void testSetTracks() {
        List<Track> tracks = new ArrayList<>();
        SpotifyTrack track1 = Mockito.mock(SpotifyTrack.class);
        SpotifyTrack track2 = Mockito.mock(SpotifyTrack.class);
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
        SpotifyRestrictions restrictions = new SpotifyRestrictions("region");
        spotifyAlbum.setRestrictions(restrictions);
        assertEquals(restrictions, spotifyAlbum.getRestrictions());
    }

    @Test
    public void testSetHref() {
        String href = "123.com";
        spotifyAlbum.setHref(href);
        assertEquals(href, spotifyAlbum.getHref());
    }

    @Test
    public void testSetImages() {
        SpotifyImage image1 = Mockito.mock(SpotifyImage.class);
        SpotifyImage image2 = Mockito.mock(SpotifyImage.class);
        ArrayList<SpotifyImage> imageList = new ArrayList<>();
        imageList.add(image1);
        imageList.add(image2);
        spotifyAlbum.setImages(imageList);
        assertEquals(imageList, spotifyAlbum.getImages());
    }

    @Test
    public void testSetId() {
        String id = "123";
        spotifyAlbum.setId(id);
        assertEquals(id, spotifyAlbum.getId());
    }

    @Test
    public void testSetAvailableMarkets() {
        String market1 = "Ohio";
        String market2 = "Also Ohio";

        ArrayList<String> marketList = new ArrayList<>();
        marketList.add(market1);
        marketList.add(market2);

        spotifyAlbum.setAvailableMarkets(marketList);

        assertEquals(marketList, spotifyAlbum.getAvailableMarkets());
    }

    @Test
    public void testSetReleaseDatePrecision() {
        String releaseDatePrecision = "12312312";

        spotifyAlbum.setReleaseDatePrecision(releaseDatePrecision);
        
        assertEquals(releaseDatePrecision, spotifyAlbum.getReleaseDatePrecision());
    }
}