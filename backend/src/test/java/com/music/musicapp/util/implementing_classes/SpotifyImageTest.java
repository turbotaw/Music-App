package com.music.musicapp.util.implementing_classes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SpotifyImageTest {

    @Test
    public void testConstructorAndGetters() {
       
        String url = "https://example.com/image.jpg";
        int height = 200;
        int width = 300;

        SpotifyImage spotifyImage = new SpotifyImage(url, height, width);

        assertEquals(url, spotifyImage.getUrl());
        assertEquals(height, spotifyImage.getHeight());
        assertEquals(width, spotifyImage.getWidth());
    }

    @Test
    public void testSetters() {

        SpotifyImage spotifyImage = new SpotifyImage("https://example.com/image.jpg", 200, 300);
        String newUrl = "https://newexample.com/image.jpg";
        int newHeight = 250;
        int newWidth = 350;

        spotifyImage.setUrl(newUrl);
        spotifyImage.setHeight(newHeight);
        spotifyImage.setWidth(newWidth);

        assertEquals(newUrl, spotifyImage.getUrl());
        assertEquals(newHeight, spotifyImage.getHeight());
        assertEquals(newWidth, spotifyImage.getWidth());
    }
}
