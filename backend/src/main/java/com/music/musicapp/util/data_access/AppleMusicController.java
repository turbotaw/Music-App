package com.music.musicapp.util.data_access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppleMusicController {

    private final AppleMusicService appleMusicService;

    @Autowired
    public AppleMusicController(AppleMusicService appleMusicService) {
        this.appleMusicService = appleMusicService;
    }

    @GetMapping("/song")
    public String getSongByISRC(@RequestParam String isrc) throws Exception {
        return appleMusicService.getSongByISRC(isrc);
    }
}