package com.music.musicapp.util.data_access;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SpotifyTokenService {

    private final SpotifyTokenRepository spotifyTokenRepository;

    @Autowired
    public SpotifyTokenService(SpotifyTokenRepository spotifyTokenRepository) {
        this.spotifyTokenRepository = spotifyTokenRepository;
    }

    public Optional<SpotifyTokenTable> setAccessToken(Long userId, String token) {
        SpotifyTokenTable tokenTable = spotifyTokenRepository.findById(userId)
                .orElse(new SpotifyTokenTable());

        tokenTable.setUserId(userId);
        tokenTable.setAccessToken(token);
        spotifyTokenRepository.save(tokenTable);

        return Optional.of(tokenTable);
    }

    public Optional<SpotifyTokenTable> setAuthorizationToken(Long userId, String token) {
        SpotifyTokenTable tokenTable = spotifyTokenRepository.findById(userId)
                .orElse(new SpotifyTokenTable());

        tokenTable.setUserId(userId);
        tokenTable.setAuthToken(token);
        spotifyTokenRepository.save(tokenTable);

        return Optional.of(tokenTable);
    }

    public Optional<String> getAuthorizationToken(Long userId) {
        Optional<SpotifyTokenTable> tokenTableOptional = spotifyTokenRepository.findById(userId);
        return tokenTableOptional.map(SpotifyTokenTable::getAuthToken);
    }

    public Optional<String> getAccessToken(Long userId) {
        Optional<SpotifyTokenTable> tokenTableOptional = spotifyTokenRepository.findById(userId);
        return tokenTableOptional.map(SpotifyTokenTable::getAccessToken);
    }

}