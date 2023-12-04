// TopTracksPage.tsx
import React, { useEffect, useState } from 'react';
import TopTracks from './TopTracks';
import  './TopTracks.css'


const TopTracksPage: React.FC = () => {
    const [trackList, setTrackList] = useState<string[]>([]);
    const userId = localStorage.getItem('userId');
    console.log(userId);

    useEffect(() => {
        const timeRange = "medium_term";
        const fetchTracks = async () => {
            if (userId) {
                try {
                    const response = await fetch(`http://localhost:8080/spotify/top-tracks?user_id=${userId}&time_range=${timeRange}`);
                    const data = await response.json();
                    console.log(data);
                    setTrackList(data);
                } catch (error) {
                    console.error("Error fetching top tracks:", error);
                }
            }
        };

        fetchTracks();
    }, [userId]);

    return (
        <div>
            <TopTracks tracks={trackList} />
        </div>
    );
};

export default TopTracksPage;