import React, { useState } from 'react';
import { UserProvider, useUser } from './UserContext';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import SpotifyRequest from './SpotifyComponent';
import { useNavigate } from 'react-router-dom';
import SpotifyComponent from './SpotifyComponent';

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
    </div>
  );
};

const App: React.FC = () => {
  return (
    <UserProvider>
      <Router>
        <Routes>
          <Route path="/spotify-request" element={<SpotifyComponent />} />
          <Route path="/" element={<HomePage />} />
        </Routes>
        <UserInput />
        <DisplayUserId />
      </Router>
    </UserProvider>
  );
};


export default App;
