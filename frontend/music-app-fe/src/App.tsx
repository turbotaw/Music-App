import React, { useState } from 'react';
import { UserProvider, useUser } from './UserContext';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import SpotifyRequest from './SpotifyRequest';
import { useNavigate } from 'react-router-dom';

const UserInput = () => {
  const [input, setInput] = useState<string>('');
  const { setUserId } = useUser();
  const navigate = useNavigate();

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

const DisplayUserId = () => {
  const { userId } = useUser();
  return <div>Submitted User ID: {userId}</div>;
};

const App = () => {
  return (
    <UserProvider>
      <Router> {/* Wrap everything within Router */}
        <div className="App">
          <Routes>
            <Route path="/spotify-request" element={<SpotifyRequest />} />
          </Routes>
          <UserInput />
          <DisplayUserId />
        </div>
      </Router>
    </UserProvider>
  );
};

export default App;
