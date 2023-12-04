import React from "react";

type TrackList = string[];

interface TopTracksProps {
    tracks: TrackList;
}

const TopTracks: React.FC<TopTracksProps> = ({ tracks }) => {
    return (
        <div className="center-container">
        <div className="top-tracks">
            <h2>Your Top Tracks Over the Past 6 Months</h2>
            <ul>
                {tracks.map((track, index) => (
                    <li key={index}>{track}</li>
                ))}
            </ul>
        </div>
        </div>
    );
};

export default TopTracks;