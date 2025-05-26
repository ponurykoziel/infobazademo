import React from 'react';
import { Button, message } from 'antd';

const AUTH_TOKEN = "test-token";

const LoginButton = ({onAfterSuccessfulLogin}) => {

  const handleLogin = () => {
    localStorage.setItem("auth_token", AUTH_TOKEN);
    onAfterSuccessfulLogin();
  };

  return (
    <Button type="primary" onClick={handleLogin}>
      Zaloguj
    </Button>
  );

};

export default LoginButton;