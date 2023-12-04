package com.music.musicapp.util.data_access;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Optional;

@RestController
@RequestMapping("/spotify")
public class SpotifyUserRequestController {

    @Autowired
    private SpotifyDataTransformService spotifyDataTransformService;

    @GetMapping("/top-tracks")
    public List<String> getTopTracks(
            @RequestParam Long user_id,
            @RequestParam String time_range,
            @RequestParam Optional<Integer> offset) throws ParseException {

        int actualOffset = offset.orElse(0);
        return spotifyDataTransformService.transformTopTracksAndArtist(actualOffset, user_id, time_range);
    }
}
