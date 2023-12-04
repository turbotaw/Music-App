import React from "react";
import Spinner from './Spinner'; 

interface TopTracksProps {
    tracks: string[];
    timeRange: 'short_term' | 'medium_term' | 'long_term';
    isLoading: boolean;
}

const TopTracks: React.FC<TopTracksProps> = ({ tracks, timeRange, isLoading }) => {
    let headerText = '';
    switch (timeRange) {
        case 'short_term':
            headerText = 'Your Top Tracks over the Past Month';
            break;
        case 'medium_term':
            headerText = 'Your Top Tracks over the Past 6 Months';
            break;
        case 'long_term':
            headerText = 'Your Top Tracks since Account Creation';
            break;
        default:
            headerText = 'Your Top Tracks';
            break;
    }

    return (
        <div className="center-container">
            <div className="top-tracks">
                <h2>{headerText}</h2>
                {isLoading ? (
                    <Spinner /> // Display spinner when loading
                ) : (
                    <ul>
                        {tracks.map((track, index) => (
                            <li key={index}>{track}</li>
                        ))}
                    </ul>
                )}
            </div>
        </div>
    );
};

export default TopTracks;