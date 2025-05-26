import React from 'react';
import { Button, Card, Divider } from 'antd';
import LoginButton from './LoginButton';

const LoginPanel = ({onAfterSuccessfulLogin}) => {


  const forceLogout = () => {
    localStorage.setItem("auth_token", "");
    onAfterSuccessfulLogin();
  };

  return (
    <Card 
      title="Ekran startowy" 
      style={{ width: 400, margin: '100px auto' }}
    >
      <div style={{ textAlign: 'center' }}>        
        
        <p style={{ margin: '20px 0' }}>Zaloguj się, żeby zobaczyć listę użytkowników</p>        
        
            <div style={{ 
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                gap: '20px'
            }}>

                <LoginButton onAfterSuccessfulLogin={onAfterSuccessfulLogin} />
                <Button type="primary" onClick={forceLogout}>Bez logowania</Button>                
            </div>
        
      </div>
    </Card>
  );
};

export default LoginPanel;