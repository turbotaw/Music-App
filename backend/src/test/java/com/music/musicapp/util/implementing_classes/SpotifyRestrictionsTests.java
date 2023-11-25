package com.music.musicapp.util.implementing_classes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SpotifyRestrictionsTests {

    @Test
    public void testConstructorAndGetReason() {

        String reason = "Some restriction reason";
        SpotifyRestrictions restrictions = new SpotifyRestrictions(reason);

        assertEquals(reason, restrictions.getReason());
    }

    @Test
    public void testSetReason() {
    
        SpotifyRestrictions restrictions = new SpotifyRestrictions("Initial reason");
        String newReason = "New reason";

        restrictions.setReason(newReason);
        assertEquals(newReason, restrictions.getReason());
    }
}