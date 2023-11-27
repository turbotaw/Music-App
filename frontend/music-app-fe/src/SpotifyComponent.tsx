import React, { useEffect } from 'react';
import useSpotifyAuth from './SpotifyAuth';

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
    const token = localStorage.getItem('code');
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
    <div>
      {!isAuthenticated ? (
        <button onClick={handleLogin}>Login with Spotify</button>
      ) : (
        <div>Authenticated with Spotify</div>
      )}
    </div>
  );
};

export default SpotifyComponent;
