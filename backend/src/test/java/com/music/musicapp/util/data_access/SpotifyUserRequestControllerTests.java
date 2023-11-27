package com.music.musicapp.util.data_access;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
public class SpotifyUserRequestControllerTests {

    private MockMvc mockMvc;

    @Mock
    private SpotifyUserRequestService spotifyUserRequestService;

    @InjectMocks
    private SpotifyUserRequestController controller;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @Test
    public void getTopTracks_ShouldReturnTracks() throws Exception {
        long userId = 1L;
        int offset = 0;
        String expectedResponse = "{\"data\":\"Your JSON data\"}";

        given(spotifyUserRequestService.getTopTracks(offset, userId)).willReturn(expectedResponse);

        mockMvc.perform(get("/spotify/top-tracks")
                .param("user_id", Long.toString(userId))
                .param("offset", Integer.toString(offset)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(not(emptyOrNullString())));
            }

}