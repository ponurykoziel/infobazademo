import axios from 'axios';

const httpClient = axios.create({
  baseURL: 'http://localhost:8080', 
});

httpClient.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("auth_token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
      console.log("${token}");    
    }
    return config;
  },
  (error) => Promise.reject(error)
);

export default httpClient;