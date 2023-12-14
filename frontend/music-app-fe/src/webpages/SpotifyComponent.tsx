import React, { useEffect, useState } from 'react';
import useSpotifyAuth from '../util/SpotifyAuth';
import { useNavigate } from 'react-router-dom';
import { useUser } from '../components/UserContext';
import "./SpotifyComponent.css";
import spotifyLogo from '../img/spotify-logo.png';


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

const SpotifyComponent: React.FC = () => {
  const { initiateAuthRequest, exchangeCodeForToken, isAuthenticated } = useSpotifyAuth();

  useEffect(() => {
    const urlParams = new URLSearchParams(window.location.search);
    const code = urlParams.get('code');
    if (code) {
      localStorage.setItem('code', code);
    }

    if (code && !isAuthenticated) {
      exchangeCodeForToken(code)
        .then(() => {
          if (isAuthenticated) {
            setAuthorizationToken();
          }
        })
        .catch(error => console.error('Error in exchangeCodeForToken:', error));

      window.history.replaceState(null, '', window.location.pathname);
    }
  }, [isAuthenticated]);

  useEffect(() => {
    if (isAuthenticated) {
      setAuthorizationToken()
        .then(() => setRefreshToken())
        .catch(error => console.error('Error in setRefreshToken:', error));
    }
  }, [isAuthenticated]);

  const handleLogin = () => {
    initiateAuthRequest();
  };

  const setRefreshToken = async () => {
    const userId = localStorage.getItem('userId');
    const refreshToken = localStorage.getItem('spotify_refresh_token');
    console.log("userId: ", userId);
    console.log("refreshToken: ", refreshToken);

    try {
      const response = await fetch(`http://localhost:8080/token/access/set?user_id=${userId}&token=${encodeURIComponent(refreshToken ?? '')}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        }
      });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      const data = await response.json();
      console.log('Response data:', data);
    } catch (error) {
      console.error('There was an error!', error);
    }
  }
  const setAuthorizationToken = async () => {
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('spotify_access_token');
    const refreshToken = localStorage.getItem('spotify_refresh_token');
    const expires_in = localStorage.getItem('spotify_token_expires_in')
    console.log("token: ", token);
    console.log("userId: ", userId);
    console.log("refreshToken: ", refreshToken);
    console.log("expires_in: ", expires_in);


    try {
      const response = await fetch(`http://localhost:8080/token/auth/set?user_id=${userId}&token=${encodeURIComponent(token ?? '')}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        }
      });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      const data = await response.json();
      console.log('Response data:', data);
    } catch (error) {
      console.error('There was an error!', error);
    }
  };

  return (
    <div className="spotify-component">
        {!isAuthenticated ? (
            <div>
                <button onClick={handleLogin}>
                Login with <img src={spotifyLogo} alt="Spotify" className="spotify-logo" />
                </button>
                <UserInput />
            </div>
        ) : (
            <div>Authenticated with Spotify</div>
        )}
        <DisplayUserId />
    </div>
);
};


export default SpotifyComponent;