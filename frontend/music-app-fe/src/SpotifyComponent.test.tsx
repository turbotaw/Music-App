import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import SpotifyComponent from './SpotifyComponent';
import useSpotifyAuth from './SpotifyAuth';

jest.mock('./SpotifyAuth', () => ({
    __esModule: true,
    default: jest.fn(),
}));

const localStorageMock = (() => {
    let store: Record<string, string> = {};

    return {
        getItem: jest.fn((key: string) => store[key] || null),
        setItem: jest.fn((key: string, value: string) => { store[key] = value; }),
        removeItem: jest.fn((key: string) => { delete store[key]; }),
        clear: jest.fn(() => { store = {}; }),
    };
})();

Object.defineProperty(window, 'localStorage', {
    value: localStorageMock
});

beforeEach(() => {
    jest.clearAllMocks();
    localStorage.clear();

    global.fetch = jest.fn(() =>
        Promise.resolve({
            ok: true,
            json: () => Promise.resolve({}),
        })
    ) as jest.Mock;
});

it('should display login button when not authenticated', () => {
    const initiateAuthRequestMock = jest.fn();
    (useSpotifyAuth as jest.Mock).mockImplementation(() => ({
        initiateAuthRequest: initiateAuthRequestMock,
        exchangeCodeForToken: jest.fn(),
        isAuthenticated: false,
    }));

    render(<SpotifyComponent />);
    const loginButton = screen.getByText('Login with Spotify');
    expect(loginButton).toBeInTheDocument();

    fireEvent.click(loginButton);
    expect(initiateAuthRequestMock).toHaveBeenCalled();
});

it('should display authenticated message when authenticated', () => {
    (useSpotifyAuth as jest.Mock).mockImplementation(() => ({
        initiateAuthRequest: jest.fn(),
        exchangeCodeForToken: jest.fn(),
        isAuthenticated: true,
    }));

    render(<SpotifyComponent />);
    expect(screen.getByText('Authenticated with Spotify')).toBeInTheDocument();
});

it('should handle code exchange on mount if code is present in URL', () => {
    Object.defineProperty(window, 'location', {
        value: {
            search: '?code=test-code',
            pathname: '/test',
            replace: jest.fn(),
        },
        writable: true,
    });

    const exchangeCodeForTokenMock = jest.fn(() => Promise.resolve());
    (useSpotifyAuth as jest.Mock).mockReturnValue({
        initiateAuthRequest: jest.fn(),
        exchangeCodeForToken: exchangeCodeForTokenMock,
        isAuthenticated: false,
    });

    render(<SpotifyComponent />);
    expect(localStorage.setItem).toHaveBeenCalledWith('code', 'test-code');
    expect(exchangeCodeForTokenMock).toHaveBeenCalledWith('test-code');
});

it('should call setAuthorizationToken and setRefreshToken when authenticated', async () => {
    localStorage.setItem('userId', 'testUserId');
    localStorage.setItem('code', 'testCode');
    localStorage.setItem('spotify_refresh_token', 'testRefreshToken');
    localStorage.setItem('spotify_token_expires_in', '3600');

    (useSpotifyAuth as jest.Mock).mockReturnValue({
        initiateAuthRequest: jest.fn(),
        exchangeCodeForToken: jest.fn(),
        isAuthenticated: true,
    });

    render(<SpotifyComponent />);
});
