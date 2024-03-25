package com.music.musicapp.util.data_access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.music.musicapp.util.auth.AppleMusicTokenGenerator;

@Service
public class AppleMusicService {

    private final AppleMusicTokenGenerator tokenGenerator;

    @Autowired
    public AppleMusicService(AppleMusicTokenGenerator tokenGenerator) {
        this.tokenGenerator = tokenGenerator;
    }

    public String getSongByISRC(String isrc) throws Exception {
        String token = tokenGenerator.generateToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.music.apple.com/v1/catalog/us/songs?filter[isrc]=" + isrc;
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
    }
}