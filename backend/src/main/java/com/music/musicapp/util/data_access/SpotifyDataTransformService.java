package com.music.musicapp.util.data_access;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.musicapp.util.implementing_classes.SpotifyTrack;


@Service
public class SpotifyDataTransformService {

    @Autowired
    private SpotifyJsonParser spotifyJsonParser;

    @Autowired
    private SpotifyUserRequestService spotifyUserRequestService;

    public List<String> transformTopTracksAndArtist(int actualOffset, Long user_id, String time_range) throws ParseException{
        List<SpotifyTrack> trackList = spotifyJsonParser.parseJsonToSpotifyTracks(spotifyUserRequestService.getTopTracks(actualOffset, user_id, time_range));
        List<String> trackAndArtist = new ArrayList<>();
        for(SpotifyTrack track : trackList){
            String trackName = track.getTrackName();
            String artistName = track.getArtist().getName();
            trackAndArtist.add(trackName + " - " + artistName);
        }
        return trackAndArtist;
    }

}
