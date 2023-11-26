import React, { useState } from 'react';

const SpotifyRequest = () => {
    const [userId, setUserId] = useState('');

    const handleSubmit = async (event: React.FormEvent) => {
      event.preventDefault(); // Prevent the default form submit action
  
      try {
        const response = await fetch('http://localhost:8080/login/spotify', {
          method: 'POST', // Assuming the endpoint expects a POST request
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ user_id: userId }), // Sending userId in the body
        });
  
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
  
        const data = await response.json();
        console.log(data); // Handle the response data
      } catch (error) {
        console.error('There was an error!', error);
      }
    };
  
    return (
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          value={userId}
          onChange={(e) => setUserId(e.target.value)}
          placeholder="Enter User ID"
        />
        <button type="submit">Submit</button>
      </form>
    );
  };
  

export default SpotifyRequest;
