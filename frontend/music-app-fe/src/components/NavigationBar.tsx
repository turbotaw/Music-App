import React from 'react';
import { Link } from 'react-router-dom';
import './NavigationBar.css';

const NavigationBar: React.FC = () => {
  return (
    <nav>
      <ul>
        <li><Link to="/">Home</Link></li>
        <li><Link to="/spotify-request">Spotify Request</Link></li>
        <li><Link to="/top-tracks">Top Tracks</Link></li>
      </ul>
    </nav>
  );
};

export default NavigationBar;