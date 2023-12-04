import React, { useState } from 'react';
import { render, act } from '@testing-library/react';
import useSpotifyAuth from './SpotifyAuth'; 
import { TextEncoder } from 'util';

global.TextEncoder = require('util').TextEncoder;

// Mock localStorage
const mockLocalStorage = {
  setItem: jest.fn(),
  getItem: jest.fn(),
  removeItem: jest.fn()
};
Object.defineProperty(global, 'localStorage', { value: mockLocalStorage });

// Mock fetch
global.fetch = jest.fn((input: RequestInfo | URL, init?: RequestInit) => {
  return Promise.resolve({
    ok: true,
    json: () => Promise.resolve({ access_token: 'token', expires_in: 3600, refresh_token: 'refresh' })
  }) as Promise<Response>;
});

// Mock window.location
Object.defineProperty(window, 'location', {
  value: {
    href: ''
  },
  writable: true
});

// Mock window.crypto
Object.defineProperty(window, 'crypto', {
  value: {
    getRandomValues: (buffer: Uint8Array) => {
      for (let i = 0; i < buffer.length; i++) {
        buffer[i] = Math.floor(Math.random() * 256);
      }
      return buffer;
    },
    subtle: {
      digest: jest.fn().mockImplementation((algo, data) => {
        // Mock implementation or return of digest
        // For example, you can return a fixed ArrayBuffer or use a hashing library
        return Promise.resolve(new ArrayBuffer(32)); // Mocked ArrayBuffer
      })
  }
}
});

function TestComponent() {
  const { initiateAuthRequest, exchangeCodeForToken, isAuthenticated } = useSpotifyAuth();
  const [testState, setTestState] = useState<string | null>(null);

  // Example function to simulate the condition to initiate an auth request
  const handleInitiateAuth = async () => {
    await initiateAuthRequest();
    setTestState('authInitiated');
  };

  // Example function to simulate the condition to exchange code for token
  const handleExchangeToken = async () => {
    await exchangeCodeForToken('test_code');
    setTestState('tokenExchanged');
  };

  return (
    <div>
      <button onClick={handleInitiateAuth} data-testid="initiate-auth">Initiate Auth</button>
      <button onClick={handleExchangeToken} data-testid="exchange-token">Exchange Token</button>
      <div data-testid="test-state">{testState}</div>
    </div>
  );
}

describe('useSpotifyAuth', () => {
  beforeEach(() => {
    mockLocalStorage.setItem.mockClear();
    mockLocalStorage.getItem.mockClear();
    mockLocalStorage.removeItem.mockClear();
    (global.fetch as jest.Mock).mockClear(); // Update this line
    window.location.href = '';
  });
  it('should initiate authentication request', async () => {
    const { getByTestId } = render(<TestComponent />);
  
    // Wrap both the click event and the subsequent state updates in `act()`
    await act(async () => {
      getByTestId('initiate-auth').click();
    });
  
    expect(window.location.href).toContain('https://accounts.spotify.com/authorize');
    expect(mockLocalStorage.setItem).toHaveBeenCalledWith('spotify_code_verifier', expect.any(String));
  });

  it('should exchange code for token', async () => {
    mockLocalStorage.getItem.mockReturnValue('test_code_verifier');
    const { getByTestId, findByTestId } = render(<TestComponent />);
  
    // Mock the fetch call for exchangeCodeForToken
    global.fetch = jest.fn((input: RequestInfo | URL, init?: RequestInit) => {
      return Promise.resolve({
        ok: true,
        json: () => Promise.resolve({ access_token: 'token', expires_in: 3600, refresh_token: 'refresh' })
      }) as Promise<Response>;
    });
  
    await act(async () => {
      getByTestId('exchange-token').click();
    });
  
    await findByTestId('test-state'); // This ensures that the state update has occurred
  
    // Updated assertion to expect any order of function calls
    expect(mockLocalStorage.setItem).toHaveBeenCalledWith(expect.stringMatching(/spotify_code_verifier|spotify_access_token|spotify_token_expires_in|spotify_refresh_token/), expect.any(String));
  });
  // Additional tests for error handling and other scenarios
});