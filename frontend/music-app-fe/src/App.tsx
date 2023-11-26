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

const App = () => {
  const [userId, setUserId] = useState<string>('');

  return (
    <UserProvider>
      <Router>
        <div className="App">
          <Routes>
            <Route path="/spotify-request" element={<SpotifyComponent />} />
          </Routes>
          <UserInput />
          <DisplayUserId />
        </div>
      </Router>
    </UserProvider>
  );
};


export default App;
