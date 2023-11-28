package com.music.musicapp.util.data_access;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.music.musicapp.util.implementing_classes.SpotifyAlbum;
import com.music.musicapp.util.implementing_classes.SpotifyArtist;
import com.music.musicapp.util.implementing_classes.SpotifyRestrictions;
import com.music.musicapp.util.implementing_classes.SpotifyTrack;

import static org.hamcrest.Matchers.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SpotifyUserRequestControllerTests {

    private MockMvc mockMvc;

    @Mock
    private SpotifyUserRequestService spotifyUserRequestService;

    @Mock
    private SpotifyJsonParser spotifyJsonParser;

    @InjectMocks
    private SpotifyUserRequestController controller;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    public void getTopTracks_ShouldReturnArtistNames() throws Exception {
        long userId = 1L;
        int offset = 0;
        String mockJsonResponse = "{\"data\":\"Your JSON data\"}";

        given(spotifyUserRequestService.getTopTracks(offset, userId)).willReturn(mockJsonResponse);
        given(spotifyJsonParser.parseJsonToSpotifyTracks(mockJsonResponse)).willReturn(createMockTracks());

        mockMvc.perform(get("/spotify/top-tracks")
                .param("user_id", String.valueOf(userId))
                .param("offset", String.valueOf(offset)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0]", is("Track 1 - Artist 1")))
                .andExpect(jsonPath("$[1]", is("Track 2 - Artist 2")));
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
}
