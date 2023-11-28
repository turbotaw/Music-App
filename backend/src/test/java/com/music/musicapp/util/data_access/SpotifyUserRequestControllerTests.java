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

import static org.hamcrest.Matchers.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SpotifyUserRequestControllerTests {

    private MockMvc mockMvc;

    @Mock
    private SpotifyDataTransformService spotifyDataTransformService;

    @InjectMocks
    private SpotifyUserRequestController controller;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    public void getTopTracks_WithOffset_ShouldReturnArtistNames() throws Exception {
        long userId = 1L;
        int offset = 0;
        List<String> mockResponse = Arrays.asList("Track 1 - Artist 1", "Track 2 - Artist 2");

        given(spotifyDataTransformService.transformTopTracksAndArtist(offset, userId)).willReturn(mockResponse);

        mockMvc.perform(get("/spotify/top-tracks")
                .param("user_id", String.valueOf(userId))
                .param("offset", String.valueOf(offset)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0]", is("Track 1 - Artist 1")))
                .andExpect(jsonPath("$[1]", is("Track 2 - Artist 2")));
    }

    @Test
    public void getTopTracks_WithoutOffset_ShouldReturnArtistNames() throws Exception {
        long userId = 1L;
        List<String> mockResponse = Arrays.asList("Track 1 - Artist 1", "Track 2 - Artist 2");

        given(spotifyDataTransformService.transformTopTracksAndArtist(0, userId)).willReturn(mockResponse);

        mockMvc.perform(get("/spotify/top-tracks")
                .param("user_id", String.valueOf(userId)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0]", is("Track 1 - Artist 1")))
                .andExpect(jsonPath("$[1]", is("Track 2 - Artist 2")));
    }
}