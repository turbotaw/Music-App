import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import { BrowserRouter as Router } from 'react-router-dom';
import App from './App';
import UserInput from './App';
import { MemoryRouter } from 'react-router-dom';
import DisplayUserId from './App';
import { UserProvider } from './components/UserContext';
import SpotifyComponent from './components/SpotifyComponent';
import { JSX } from 'react/jsx-runtime';
import { createMemoryHistory } from 'history';


jest.mock('./SpotifyComponent', () => () => <div>Mock SpotifyComponent</div>);

describe('App', () => {
  const renderApp = () => {
    render(
      <UserProvider>
        <App />
      </UserProvider>
    );
  };

  it('renders App component correctly', () => {
    renderApp();
    expect(screen.getByPlaceholderText('Enter user ID')).toBeInTheDocument();
    expect(screen.getByRole('button', { name: 'Submit' })).toBeInTheDocument();
  });

  it('updates and displays user ID on submit', async () => {
    renderApp();

    const input = screen.getByPlaceholderText('Enter user ID');
    const button = screen.getByRole('button', { name: 'Submit' });

    fireEvent.change(input, { target: { value: '12345' } });
    fireEvent.click(button);

    await waitFor(() => {
      expect(screen.getByText('Submitted User ID: 12345')).toBeInTheDocument();
    });
  });

  it('navigates to SpotifyComponent on submit', async () => {
    renderApp();

    const input = screen.getByPlaceholderText('Enter user ID');
    const button = screen.getByRole('button', { name: 'Submit' });

    fireEvent.change(input, { target: { value: '12345' } });
    fireEvent.click(button);

    await waitFor(() => {
      expect(screen.getByText('Mock SpotifyComponent')).toBeInTheDocument();
    });
  });
});
