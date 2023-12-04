import React, { useState } from 'react';
import { UserProvider, useUser } from './components/UserContext';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import SpotifyRequest from './components/SpotifyComponent';
import { useNavigate } from 'react-router-dom';
import SpotifyComponent from './components/SpotifyComponent';
import TopTracksPage from './webpages/TopTracksPage';
import NavigationBar from './components/NavigationBar';
import './App.css';

const HomePage: React.FC = () => {
  return (
    <div>
      <h1>Welcome to the Home Page</h1>
      <nav>
        <Link to="/">Home</Link> |{' '}
        <Link to="/spotify-request">Spotify Request</Link> |{' '}
        <Link to="/top-tracks">Top Tracks</Link>
      </nav>
    </div>
  );
};

const App: React.FC = () => {
  return (
    <UserProvider>
      <Router>
      <NavigationBar />
        <Routes>
          <Route path="/spotify-request" element={<SpotifyComponent />} />
          <Route path="/top-tracks" element={<TopTracksPage />} />
          <Route path="/" element={<HomePage />} />
        </Routes>
      </Router>
    </UserProvider>
  );
};


export default App;
