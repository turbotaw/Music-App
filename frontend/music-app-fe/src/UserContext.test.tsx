import { render, act } from '@testing-library/react';
import { UserProvider } from './UserContext';
import React from 'react';
import { useUser } from './UserContext';

// Mock localStorage
const mockLocalStorage = (() => {
    let store: { [key: string]: string } = {};
    return {
        getItem(key: string | number) {
            return store[key.toString()] || null;
        },
        setItem(key: string | number, value: { toString: () => string; }) {
            store[key.toString()] = value.toString();
        },
        clear() {
            store = {};
        }
    };
})();

Object.defineProperty(window, 'localStorage', {
    value: mockLocalStorage
});

const TestComponent = () => {
    const { userId, setUserId } = useUser();
    return (
        <div>
            <span data-testid="userId">{userId}</span>
            <button onClick={() => setUserId('newUserId')}>Change User</button>
        </div>
    );
};

describe('UserContext', () => {
    beforeEach(() => {
        window.localStorage.clear();
        window.localStorage.setItem('userId', 'initialUserId');
    });

    test('initializes userId from localStorage', () => {
        const { getByTestId } = render(
            <UserProvider>
                <TestComponent />
            </UserProvider>
        );
        expect(getByTestId('userId').textContent).toBe('initialUserId');
    });

    test('updates userId and localStorage when setUserId is called', () => {
        const { getByTestId, getByText } = render(
            <UserProvider>
                <TestComponent />
            </UserProvider>
        );
        act(() => {
            getByText('Change User').click();
        });
        expect(getByTestId('userId').textContent).toBe('newUserId');
        expect(window.localStorage.getItem('userId')).toBe('newUserId');
    });
});