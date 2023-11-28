package com.music.musicapp.util.data_access;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.music.musicapp.util.implementing_classes.SpotifyTrack;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/spotify")
public class SpotifyUserRequestController {

    @Autowired
    private SpotifyUserRequestService spotifyUserRequestService;

    @Autowired
    private SpotifyJsonParser spotifyJsonParser;

    @GetMapping("/top-tracks")
    public List<String> getTopTracks(
            @RequestParam Long user_id,
            @RequestParam Optional<Integer> offset) throws ParseException {

        int actualOffset = offset.orElse(0);
        String topTracks = spotifyUserRequestService.getTopTracks(actualOffset, user_id);
        List<SpotifyTrack> trackList = spotifyJsonParser.parseJsonToSpotifyTracks(spotifyUserRequestService.getTopTracks(actualOffset, user_id));
        List<String> trackAndArtist = new ArrayList<>();
        for(SpotifyTrack track : trackList){
            String trackName = track.getTrackName();
            String artistName = track.getArtist().getName();
            trackAndArtist.add(trackName + " - " + artistName);
        }
        return trackAndArtist;
    }
}
