// TopTracksPage.tsx
import React, { useEffect, useState } from 'react';
import TopTracks from '../components/TopTracks';
import  './TopTracks.css'


const TopTracksPage: React.FC = () => {
    const [shortTermTracks, setShortTermTracks] = useState<string[]>([]);
    const [mediumTermTracks, setMediumTermTracks] = useState<string[]>([]);
    const [longTermTracks, setLongTermTracks] = useState<string[]>([]);
    const [isLoadingShort, setIsLoadingShort] = useState(true);
    const [isLoadingMedium, setIsLoadingMedium] = useState(true);
    const [isLoadingLong, setIsLoadingLong] = useState(true);
    const userId = localStorage.getItem('userId');

    const fetchTracks = async (timeRange: string) => {
        if (userId) {
            try {
                const response = await fetch(`http://localhost:8080/spotify/top-tracks?user_id=${userId}&time_range=${timeRange}`);
                const data = await response.json();
                if (timeRange === 'short_term') {
                    setShortTermTracks(data);
                    setIsLoadingShort(false);
                } else if (timeRange === 'medium_term') {
                    setMediumTermTracks(data);
                    setIsLoadingMedium(false);
                } else if (timeRange === 'long_term') {
                    setLongTermTracks(data);
                    setIsLoadingLong(false);
                }
            } catch (error) {
                console.error(`Error fetching top tracks for ${timeRange}:`, error);
            }
        }
    };

    useEffect(() => {
        fetchTracks('short_term');
        fetchTracks('medium_term');
        fetchTracks('long_term');
    }, [userId]);

    return (
        <div className="tracks-container">
            <TopTracks tracks={shortTermTracks} timeRange="short_term" isLoading={isLoadingShort} />
            <TopTracks tracks={mediumTermTracks} timeRange="medium_term" isLoading={isLoadingMedium} />
            <TopTracks tracks={longTermTracks} timeRange="long_term" isLoading={isLoadingLong} />
        </div>
    );
};

export default TopTracksPage;