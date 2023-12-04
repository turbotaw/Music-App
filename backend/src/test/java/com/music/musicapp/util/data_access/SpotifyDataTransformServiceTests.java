package com.music.musicapp.util.data_access;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Date;
import java.text.ParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.music.musicapp.util.implementing_classes.SpotifyAlbum;
import com.music.musicapp.util.implementing_classes.SpotifyArtist;
import com.music.musicapp.util.implementing_classes.SpotifyRestrictions;
import com.music.musicapp.util.implementing_classes.SpotifyTrack;

@ExtendWith(MockitoExtension.class)
public class SpotifyDataTransformServiceTests {

    @Mock
    private SpotifyJsonParser spotifyJsonParser;

    @Mock
    private SpotifyUserRequestService spotifyUserRequestService;

    @InjectMocks
    private SpotifyDataTransformService spotifyDataTransformService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getTopTracks_ShouldReturnArtistNames() throws Exception {
        long userId = 1L;
        int offset = 0;
        String timeFrame = "medium_term";
        String mockJsonResponse = "{\"data\":\"Random JSON data\"}";
        List<SpotifyTrack> mockTracks = createMockTracks();

        given(spotifyUserRequestService.getTopTracks(offset, userId, timeFrame)).willReturn(mockJsonResponse);
        given(spotifyJsonParser.parseJsonToSpotifyTracks(mockJsonResponse)).willReturn(mockTracks);

        List<String> trackAndArtist = spotifyDataTransformService.transformTopTracksAndArtist(offset, userId,timeFrame);
        assertNotNull(trackAndArtist, "Returned list should not be null");
        assertEquals(2, trackAndArtist.size(), "Returned list size should match the number of tracks");
        assertEquals("Track 1 - Artist 1", trackAndArtist.get(0), "First element should match expected format");
        assertEquals("Track 2 - Artist 2", trackAndArtist.get(1), "Second element should match expected format");
    }

    private List<SpotifyTrack> createMockTracks() {
        List<SpotifyTrack> mockTracks = new ArrayList<>();

        // Mock Artist
        SpotifyArtist artist1 = new SpotifyArtist("Artist 1", new ArrayList<>(), new ArrayList<>(), "artistId1", 50,
                "artist", "uriArtist1");
        SpotifyArtist artist2 = new SpotifyArtist("Artist 2", new ArrayList<>(), new ArrayList<>(), "artistId2", 55,
                "artist", "uriArtist2");

        // Mock Album
        SpotifyAlbum album1 = new SpotifyAlbum("Album 1", 10, new Date(), Collections.singletonList(artist1), "Genre 1",
                new ArrayList<>(), "album", new ArrayList<>(), "href1", "albumId1", new ArrayList<>(), "day",
                new SpotifyRestrictions(""));
        SpotifyAlbum album2 = new SpotifyAlbum("Album 2", 8, new Date(), Collections.singletonList(artist2), "Genre 2",
                new ArrayList<>(), "album", new ArrayList<>(), "href2", "albumId2", new ArrayList<>(), "day",
                new SpotifyRestrictions(""));

        // Mock Track 1
        SpotifyTrack track1 = new SpotifyTrack("Track 1", artist1, album1, 300000, "isrc1", "uri1", true, "previewUrl1",
                1, false);
        mockTracks.add(track1);

        // Mock Track 2
        SpotifyTrack track2 = new SpotifyTrack("Track 2", artist2, album2, 200000, "isrc2", "uri2", true, "previewUrl2",
                2, false);
        mockTracks.add(track2);

        return mockTracks;
    }

    @Test
    public void shouldThrowParseException() throws ParseException {

        int actualOffset = 0;
        Long userId = 1L;
        String timeFrame = "medium_term";

        String dummyJsonResponse = "{\"data\":\"Random JSON data\"}";
        when(spotifyUserRequestService.getTopTracks(actualOffset, userId, timeFrame)).thenReturn(dummyJsonResponse);

        when(spotifyJsonParser.parseJsonToSpotifyTracks(dummyJsonResponse))
                .thenThrow(new ParseException("Parse error", 0));

        assertThrows(ParseException.class,
                () -> spotifyDataTransformService.transformTopTracksAndArtist(actualOffset, userId, timeFrame));
    }
}