import React, { useState, createContext, useContext, ReactNode, useEffect, FC } from 'react';

interface UserContextType {
    userId: string;
    setUserId: (id: string) => void;
  }
  
  // Create the context
  const UserContext = createContext<UserContextType>({
    userId: '',
    setUserId: () => {}
  });
  
  // Define the type for UserProvider props
  interface UserProviderProps {
    children: ReactNode;
  }
  
  // UserProvider component
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
  
  // Custom hook to use the context
  export const useUser = () => useContext(UserContext);