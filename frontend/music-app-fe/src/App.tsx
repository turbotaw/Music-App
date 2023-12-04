import React, { useState } from 'react';
import { UserProvider, useUser } from './UserContext';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import SpotifyRequest from './SpotifyComponent';
import { useNavigate } from 'react-router-dom';
import SpotifyComponent from './SpotifyComponent';
import TopTracksPage from './TopTracksPage';

interface UserInputProps {
  setUserId: (userId: string) => void;
}

const UserInput: React.FC = () => {
  const [input, setInput] = useState<string>('');
  const navigate = useNavigate();
  const { setUserId } = useUser();


  const handleSubmit = () => {
    setUserId(input);
    navigate('/spotify-request');
  };

  return (
    <div>
      <input
        type="text"
        value={input}
        onChange={(e) => setInput(e.target.value)}
        placeholder="Enter user ID"
      />
      <button onClick={handleSubmit}>Submit</button>
    </div>
  );
};


const DisplayUserId: React.FC = () => {
  const { userId } = useUser();

  return <div>Submitted User ID: {userId}</div>;
};

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
        <Routes>
          <Route path="/spotify-request" element={<SpotifyComponent />} />
          <Route path="/top-tracks" element={<TopTracksPage />} />
          <Route path="/" element={<HomePage />} />
        </Routes>
        <UserInput />
        <DisplayUserId />
      </Router>
    </UserProvider>
  );
};


export default App;
