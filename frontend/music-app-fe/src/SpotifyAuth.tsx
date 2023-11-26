import { useState } from 'react';
import CryptoJS from 'crypto-js';

const useSpotifyAuth = () => {
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);
  const blockingDelay = (milliseconds: number) => {
    const start = Date.now();
    let current = null;
    do {
      current = Date.now();
    } while (current - start < milliseconds);
  };
  const generateRandomString = (length: number): string => {
    const possible = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    const values = window.crypto.getRandomValues(new Uint8Array(length));
    return Array.from(values).map(x => possible[x % possible.length]).join('');
  };

  const sha256 = async (plain: string): Promise<ArrayBuffer> => {
    const encoder = new TextEncoder();
    const data = encoder.encode(plain);
    return window.crypto.subtle.digest('SHA-256', data);
  };
  const base64urlencode = (arrayBuffer: ArrayBuffer): string => {
    return btoa(String.fromCharCode(...new Uint8Array(arrayBuffer)))
      .replace(/=/g, '')
      .replace(/\+/g, '-')
      .replace(/\//g, '_');
  };

  const generateCodeChallenge = async (verifier: string): Promise<string> => {
    const hashed = await sha256(verifier);
    return base64urlencode(hashed);
  };
  const initiateAuthRequest = async (): Promise<void> => {
    const clientId = process.env.REACT_APP_SPOTIFY_CLIENT_ID;
    const redirectUri = encodeURIComponent(process.env.REACT_APP_SPOTIFY_REDIRECT_URI ?? '');
    const scopes = encodeURIComponent('user-read-private user-read-email');
    
    const codeVerifier = generateRandomString(64);
    const codeChallenge = await generateCodeChallenge(codeVerifier);
  
    localStorage.setItem('spotify_code_verifier', codeVerifier);
    const authUrl = `https://accounts.spotify.com/authorize?client_id=${clientId}&response_type=code&redirect_uri=${redirectUri}&scope=${scopes}&code_challenge_method=S256&code_challenge=${codeChallenge}`;
    window.location.href = authUrl;
  };

  const exchangeCodeForToken = async (code: string): Promise<void> => {
    const codeVerifier = localStorage.getItem('spotify_code_verifier');
    console.log('Retrieved code verifier:', codeVerifier); // Log the retrieved code verifier

    const redirectUri = process.env.REACT_APP_SPOTIFY_REDIRECT_URI;
    const clientId = process.env.REACT_APP_SPOTIFY_CLIENT_ID;

    const response = await fetch('https://accounts.spotify.com/api/token', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      body: `client_id=${clientId}&grant_type=authorization_code&code=${code}&redirect_uri=${redirectUri}&code_verifier=${codeVerifier}`
    });

    if (!response.ok) {
      throw new Error('Failed to exchange authorization code for token');
    }

    const data = await response.json();
    localStorage.setItem('spotify_access_token', data.access_token);
    localStorage.setItem('spotify_token_expires_in', data.expires_in);
    localStorage.setItem('spotify_refresh_token', data.refresh_token);
    setIsAuthenticated(true);
  };

  return { initiateAuthRequest, exchangeCodeForToken, isAuthenticated };
};

export default useSpotifyAuth;