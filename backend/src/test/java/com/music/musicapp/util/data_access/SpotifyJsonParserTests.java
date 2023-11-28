package com.music.musicapp.util.data_access;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.music.musicapp.util.implementing_classes.SpotifyTrack;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SpotifyJsonParserTests {

    private SpotifyJsonParser parser;
    private String validJsonResponse; 
    private String invalidJsonResponse; 
    private String differentDateFormatsJsonResponse; 
    private String invalidDateJsonResponse;
    private String invalidPreviewUrlJsonResponse;

    @BeforeEach
    public void setUp() {
        parser = new SpotifyJsonParser();

        // Valid JSON Response
        validJsonResponse = "{ \"items\": ["
                            + "    { \"name\": \"Track 1\", \"uri\": \"uri1\", \"external_ids\": {\"isrc\": \"isrc1\"}, \"is_playable\": false, \"preview_url\": \"url1\", \"track_number\": 1, \"duration_ms\": 300000, "
                            + "      \"album\": { \"name\": \"Album 1\", \"total_tracks\": 10, \"release_date\": \"2023-01-01\", \"album_type\": \"album\", \"available_markets\": [\"US\", \"GB\"], \"href\": \"albumHref1\", \"id\": \"albumId1\", "
                            + "                 \"images\": [{\"url\": \"imageUrl1\", \"height\": 640, \"width\": 640}], \"release_date_precision\": \"day\", \"artists\": [{\"name\": \"Album Artist 1\", \"id\": \"albumArtistId1\", \"type\": \"artist\", \"uri\": \"uriAlbumArtist1\"}] }, "
                            + "      \"artists\": [{\"name\": \"Artist 1\", \"id\": \"artist1\", \"type\": \"artist\", \"uri\": \"uriArtist1\"}] }"
                            + "] }";

        // Invalid JSON Response
        invalidJsonResponse = "{ \"items\": \"invalidContent\" }";

        differentDateFormatsJsonResponse = "{ \"items\": ["
    + "    { \"name\": \"Track 1\", \"uri\": \"uri1\", \"external_ids\": {\"isrc\": \"isrc1\"}, \"is_playable\": false, \"preview_url\": \"url1\", \"track_number\": 1, \"duration_ms\": 300000, "
    + "      \"album\": { \"name\": \"Album 1\", \"total_tracks\": 10, \"release_date\": \"2023\", \"album_type\": \"album\", \"available_markets\": [\"US\", \"GB\"], \"href\": \"albumHref1\", \"id\": \"albumId1\", "
    + "                 \"images\": [{\"url\": \"imageUrl1\", \"height\": 640, \"width\": 640}], \"release_date_precision\": \"year\", \"artists\": [{\"name\": \"Album Artist 1\", \"id\": \"albumArtistId1\", \"type\": \"artist\", \"uri\": \"uriAlbumArtist1\"}] }, "
    + "      \"artists\": [{\"name\": \"Artist 1\", \"id\": \"artist1\", \"type\": \"artist\", \"uri\": \"uriArtist1\"}] },"
    + "    { \"name\": \"Track 2\", \"uri\": \"uri2\", \"external_ids\": {\"isrc\": \"isrc2\"}, \"is_playable\": false, \"preview_url\": \"url2\", \"track_number\": 2, \"duration_ms\": 250000, "
    + "      \"album\": { \"name\": \"Album 2\", \"total_tracks\": 8, \"release_date\": \"2023-03\", \"album_type\": \"single\", \"available_markets\": [\"US\", \"CA\"], \"href\": \"albumHref2\", \"id\": \"albumId2\", "
    + "                 \"images\": [{\"url\": \"imageUrl2\", \"height\": 640, \"width\": 640}], \"release_date_precision\": \"month\", \"artists\": [{\"name\": \"Album Artist 2\", \"id\": \"albumArtistId2\", \"type\": \"artist\", \"uri\": \"uriAlbumArtist2\"}] }, "
    + "      \"artists\": [{\"name\": \"Artist 2\", \"id\": \"artist2\", \"type\": \"artist\", \"uri\": \"uriArtist2\"}] },"
    + "    { \"name\": \"Track 3\", \"uri\": \"uri3\", \"external_ids\": {\"isrc\": \"isrc3\"}, \"is_playable\": false, \"preview_url\": \"url3\", \"track_number\": 3, \"duration_ms\": 200000, "
    + "      \"album\": { \"name\": \"Album 3\", \"total_tracks\": 12, \"release_date\": \"2023-03-01\", \"album_type\": \"EP\", \"available_markets\": [\"US\", \"UK\"], \"href\": \"albumHref3\", \"id\": \"albumId3\", "
    + "                 \"images\": [{\"url\": \"imageUrl3\", \"height\": 640, \"width\": 640}], \"release_date_precision\": \"day\", \"artists\": [{\"name\": \"Album Artist 3\", \"id\": \"albumArtistId3\", \"type\": \"artist\", \"uri\": \"uriAlbumArtist3\"}] }, "
    + "      \"artists\": [{\"name\": \"Artist 3\", \"id\": \"artist3\", \"type\": \"artist\", \"uri\": \"uriArtist3\"}] }"
    + "] }";
        invalidDateJsonResponse = "{ \"items\": ["
                            + "    { \"name\": \"Track 1\", \"uri\": \"uri1\", \"external_ids\": {\"isrc\": \"isrc1\"}, \"is_playable\": false, \"preview_url\": \"url3\",\"track_number\": 1, \"duration_ms\": 300000, "
                            + "      \"album\": { \"name\": \"Album 1\", \"total_tracks\": 10, \"release_date\": \"!!fnk2\", \"album_type\": \"album\", \"available_markets\": [\"US\", \"GB\"], \"href\": \"albumHref1\", \"id\": \"albumId1\", "
                            + "                 \"images\": [{\"url\": \"imageUrl1\", \"height\": 640, \"width\": 640}], \"release_date_precision\": \"day\", \"artists\": [{\"name\": \"Album Artist 1\", \"id\": \"albumArtistId1\", \"type\": \"artist\", \"uri\": \"uriAlbumArtist1\"}] }, "
                            + "      \"artists\": [{\"name\": \"Artist 1\", \"id\": \"artist1\", \"type\": \"artist\", \"uri\": \"uriArtist1\"}] }"
                            + "] }";
        invalidPreviewUrlJsonResponse = "{ \"items\": ["
                            + "    { \"name\": \"Track 1\", \"uri\": \"uri1\", \"external_ids\": {\"isrc\": \"isrc1\"}, \"is_playable\":\"false\", \"track_number\": 1, \"duration_ms\": 300000, "
                            + "      \"album\": { \"name\": \"Album 1\", \"total_tracks\": 10, \"release_date\": \"2022\", \"album_type\": \"album\", \"available_markets\": [\"US\", \"GB\"], \"href\": \"albumHref1\", \"id\": \"albumId1\", "
                            + "                 \"images\": [{\"url\": \"imageUrl1\", \"height\": 640, \"width\": 640}], \"release_date_precision\": \"day\", \"artists\": [{\"name\": \"Album Artist 1\", \"id\": \"albumArtistId1\", \"type\": \"artist\", \"uri\": \"uriAlbumArtist1\"}] }, "
                            + "      \"artists\": [{\"name\": \"Artist 1\", \"id\": \"artist1\", \"type\": \"artist\", \"uri\": \"uriArtist1\"}] },"
                            + "    { \"name\": \"Track 2\", \"uri\": \"uri1\", \"external_ids\": {\"isrc\": \"isrc1\"}, \"is_playable\": false, \"preview_url\": null, \"track_number\": 1, \"duration_ms\": 300000, "
                            + "      \"album\": { \"name\": \"Album 1\", \"total_tracks\": 10, \"release_date\": \"2022\", \"album_type\": \"album\", \"available_markets\": [\"US\", \"GB\"], \"href\": \"albumHref1\", \"id\": \"albumId1\", "
                            + "                 \"images\": [{\"url\": \"imageUrl1\", \"height\": 640, \"width\": 640}], \"release_date_precision\": \"day\", \"artists\": [{\"name\": \"Album Artist 1\", \"id\": \"albumArtistId1\", \"type\": \"artist\", \"uri\": \"uriAlbumArtist1\"}] }, "
                            + "      \"artists\": [{\"name\": \"Artist 1\", \"id\": \"artist1\", \"type\": \"artist\", \"uri\": \"uriArtist1\"}] }"
                            + "] }";
    }

    @Test
    public void testParseJsonToSpotifyTracks_validResponse() {
        try {
            List<SpotifyTrack> tracks = parser.parseJsonToSpotifyTracks(validJsonResponse);
            assertNotNull(tracks, "Track list should not be null");
            assertFalse(tracks.isEmpty(), "Track list should not be empty");

            // Additional assertions to check the correctness of parsed data
            SpotifyTrack firstTrack = tracks.get(0);
            assertEquals("Track 1", firstTrack.getTrackName(), "Track name should match");
            assertEquals("uri1", firstTrack.getUri(), "Track URI should match");
            assertEquals("Artist 1", firstTrack.getArtist().getName(), "Artist name should match");
            // Add more assertions as needed

        } catch (ParseException e) {
            fail("ParseException should not be thrown for a valid JSON response");
        }
    }

    @Test
    public void testParseJsonToSpotifyTracks_validResponse_differentDateFormats() {
        try {
            List<SpotifyTrack> tracks = parser.parseJsonToSpotifyTracks(differentDateFormatsJsonResponse);
            assertNotNull(tracks, "Track list should not be null");
            assertFalse(tracks.isEmpty(), "Track list should not be empty");

            // Additional assertions to check the correctness of parsed data
            SpotifyTrack firstTrack = tracks.get(0);
            assertEquals("Track 1", firstTrack.getTrackName(), "Track name should match");
            assertEquals("uri1", firstTrack.getUri(), "Track URI should match");
            assertEquals("Artist 1", firstTrack.getArtist().getName(), "Artist name should match");
            // Add more assertions as needed

        } catch (ParseException e) {
            fail("ParseException should not be thrown for a valid JSON response");
        }
    }

    @Test
    public void testParseJsonToSpotifyTracks_emptyResponse() {
        assertThrows(ParseException.class, () -> {
            parser.parseJsonToSpotifyTracks("");
        });
    }

    @Test
    public void testParseJsonToSpotifyTracks_nullResponse() {
        assertThrows(ParseException.class, () -> {
            parser.parseJsonToSpotifyTracks(null);
        });
    }

    @Test
    public void testParseJsonToSpotifyTracks_invalidJsonResponse() {
        assertThrows(ParseException.class, () -> {
            parser.parseJsonToSpotifyTracks(invalidJsonResponse);
        });
    }

    @Test
    public void testParseJsonToSpotifyTracks_unexpectedDateFormat() {
        assertThrows(ParseException.class, () -> {
            parser.parseJsonToSpotifyTracks(invalidDateJsonResponse);
        });
    }

     @Test
    public void testPreviewUrlHandling() throws ParseException {
        List<SpotifyTrack> tracks = parser.parseJsonToSpotifyTracks(invalidPreviewUrlJsonResponse);

        assertTrue(tracks.get(0).getPreviewUrl().isEmpty(), "First track should not have a preview URL");
        assertNotNull(tracks.get(0).getPreviewUrl(), "First track should not have a preview URL");

        assertTrue((tracks.get(0).isPlayable()), "First track should be playable");
        assertTrue(tracks.get(1).getPreviewUrl().isEmpty(), "Second track should not have a preview URL");
    }
}