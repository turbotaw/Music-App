package com.music.musicapp.util.data_access;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.music.musicapp.util.implementing_classes.SpotifyAlbum;
import com.music.musicapp.util.implementing_classes.SpotifyArtist;
import com.music.musicapp.util.implementing_classes.SpotifyImage;
import com.music.musicapp.util.implementing_classes.SpotifyRestrictions;
import com.music.musicapp.util.implementing_classes.SpotifyTrack;
import com.music.musicapp.util.interfaces.Album;
import com.music.musicapp.util.interfaces.Artist;
import com.music.musicapp.util.interfaces.Track;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SpotifyJsonParser {

    public List<SpotifyTrack> parseJsonToSpotifyTracks(String jsonResponse) throws ParseException {
        if (jsonResponse == null) {
            throw new ParseException("Input JSON string is null", 0);
        }

        try {
            List<SpotifyTrack> tracks = new ArrayList<>();

            JSONObject obj = new JSONObject(jsonResponse);
            JSONArray items = obj.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                JSONObject trackJson = items.getJSONObject(i);

                // Parse track details
                String trackName = trackJson.getString("name");
                String uri = trackJson.getString("uri");
                String isrcId = trackJson.getJSONObject("external_ids").getString("isrc");
                boolean isPlayable = true;
                String previewUrl = "";
                if (trackJson.has("preview_url") && !trackJson.isNull("preview_url")) {
                    previewUrl = trackJson.getString("preview_url");
                }
                int trackNumber = trackJson.getInt("track_number");
                int durationMs = trackJson.getInt("duration_ms");

                // Parse album details
                JSONObject albumJson = trackJson.getJSONObject("album");
                String albumName = albumJson.getString("name");
                int totalTracks = albumJson.getInt("total_tracks");

                String releaseDateStr = albumJson.getString("release_date");
                SimpleDateFormat dateFormat;
                Date releaseDate;

                switch (releaseDateStr.length()) {
                    case 4: // Only year is provided
                        dateFormat = new SimpleDateFormat("yyyy");
                        break;
                    case 7: // Year and month are provided
                        dateFormat = new SimpleDateFormat("yyyy-MM");
                        break;
                    case 10: // Full date is provided
                        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        break;
                    default:
                        // Handle unexpected format
                        throw new ParseException("Unexpected date format: " + releaseDateStr, 0);
                }

                releaseDate = dateFormat.parse(releaseDateStr);

                JSONArray albumArtistsJson = albumJson.getJSONArray("artists");
                List<Artist> albumArtists = parseArtists(albumArtistsJson);
                String genre = ""; // Genre may not be directly available
                List<Track> tracksInAlbum = new ArrayList<>(); // Tracks in album may not be directly available
                String albumType = albumJson.getString("album_type");
                JSONArray marketsJson = albumJson.getJSONArray("available_markets");
                List<String> availableMarkets = parseStringArray(marketsJson);
                String href = albumJson.getString("href");
                String albumId = albumJson.getString("id");
                JSONArray imagesJson = albumJson.getJSONArray("images");
                List<SpotifyImage> images = parseSpotifyImages(imagesJson);
                String releaseDatePrecision = albumJson.getString("release_date_precision");
                SpotifyRestrictions restrictions = new SpotifyRestrictions(""); // Restrictions may not be directly
                                                                                // available

                SpotifyAlbum album = new SpotifyAlbum(albumName, totalTracks, releaseDate, albumArtists, genre,
                        tracksInAlbum, albumType, availableMarkets, href, albumId, images, releaseDatePrecision,
                        restrictions);

                // Parse artist details
                JSONArray artistsJson = trackJson.getJSONArray("artists");
                List<Artist> artists = parseArtists(artistsJson);

                SpotifyTrack track = new SpotifyTrack(trackName, artists.get(0), album, durationMs, isrcId, uri,
                        isPlayable, previewUrl, trackNumber, false);
                tracks.add(track);
            }

            return tracks;
        } catch (JSONException e) {
            throw new ParseException("Error parsing JSON: " + e.getMessage(), 0);
        }
    }

    private List<Artist> parseArtists(JSONArray artistsJson) {
        List<Artist> artists = new ArrayList<>();
        for (int j = 0; j < artistsJson.length(); j++) {
            JSONObject artistJson = artistsJson.getJSONObject(j);
            String artistName = artistJson.getString("name");
            String artistId = artistJson.getString("id");
            int popularity = 0; // Popularity may not be directly available
            String type = artistJson.getString("type");
            String artistUri = artistJson.getString("uri");
            List<String> genres = new ArrayList<>(); // Genres may not be directly available
            List<Album> albums = new ArrayList<>(); // Albums may not be directly available

            SpotifyArtist artist = new SpotifyArtist(artistName, genres, albums, artistId, popularity, type, artistUri);
            artists.add(artist);
        }
        return artists;
    }

    private List<String> parseStringArray(JSONArray jsonArray) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            strings.add(jsonArray.getString(i));
        }
        return strings;
    }

    private List<SpotifyImage> parseSpotifyImages(JSONArray imagesJson) {
        List<SpotifyImage> images = new ArrayList<>();
        for (int j = 0; j < imagesJson.length(); j++) {
            JSONObject imageJson = imagesJson.getJSONObject(j);
            int height = imageJson.getInt("height");
            int width = imageJson.getInt("width");
            String url = imageJson.getString("url");

            SpotifyImage image = new SpotifyImage(url, height, width);
            images.add(image);
        }
        return images;
    }
}