package com.music.musicapp.util.data_access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/spotify")
public class SpotifyUserRequestController {

    @Autowired
    private SpotifyUserRequestService spotifyUserRequestService;

    @GetMapping("/top-tracks")
    public ResponseEntity<String> getTopTracks(
            @RequestParam Long user_id,
            @RequestParam Optional<Integer> offset) {

        int actualOffset = offset.orElse(0);
        String topTracks = spotifyUserRequestService.getTopTracks(actualOffset, user_id);

        return ResponseEntity.ok(topTracks);
    }
}
