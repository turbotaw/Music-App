import React, { useState, createContext, useContext, ReactNode, useEffect, FC } from 'react';

interface UserContextType {
  userId: string;
  setUserId: (id: string) => void;
}

const UserContext = createContext<UserContextType>({
  userId: '',
  setUserId: () => { }
});

interface UserProviderProps {
  children: ReactNode;
}

export const UserProvider: FC<UserProviderProps> = ({ children }) => {
  const [userId, setUserIdState] = useState<string>(localStorage.getItem('userId') || '');

  const setUserId = (newUserId: string) => {
    localStorage.setItem('userId', newUserId);
    setUserIdState(newUserId);
  };

  useEffect(() => {
    const storedUserId = localStorage.getItem('userId');
    if (storedUserId) {
      setUserIdState(storedUserId);
    }
  }, []);

  return (
    <UserContext.Provider value={{ userId, setUserId }}>
      {children}
    </UserContext.Provider>
  );
};

export const useUser = () => useContext(UserContext);