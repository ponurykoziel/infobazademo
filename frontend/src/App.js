import React from 'react';
import { useState } from 'react';
import 'antd/dist/reset.css'; 

import LoginPanel from './files/LoginPanel';
import Roster from './files/Roster';


function App() {

  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const onAfterSuccessfulLogin = () => {    
    setIsLoggedIn(true);
    
  };

  return (
      <> { 
        isLoggedIn 
        ? ( <Roster/> )
        : ( <LoginPanel onAfterSuccessfulLogin={onAfterSuccessfulLogin}/> )
      } </>          
  );

}

export default App;
