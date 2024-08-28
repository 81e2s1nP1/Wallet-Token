import React, { useState } from 'react';
import './Login.css';
import UserService from '../services/UserServices';
import { useNavigate } from 'react-router-dom';
import { FaGoogle, FaGithub } from 'react-icons/fa';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [showAlert, setShowAlert] = useState(false);
  const navigate = useNavigate();

  async function handleSubmit(e) {
    e.preventDefault();
    const login = { email, password };
    try {
      const response = await UserService.login(login);
      const token = 'Bearer ' + response.data.accessToken;
      const role = response.data.role;
      UserService.storeToken(token);
      UserService.SaveLoggerInUser(login.email, role);
      localStorage.setItem('token', token); // Store token in localStorage
      navigate('/');
      window.location.reload(false); // Optional: Refresh the page to reflect changes
    } catch (err) {
      setShowAlert(true); // Show the alert on login failure
      console.error(err);
    }
  }

  const handleGoogleLogin = () => {
    // Implement Google login logic here
  };

  const handleGithubLogin = () => {
    // Implement GitHub login logic here
  };

  const handleCloseAlert = () => {
    setShowAlert(false);
  };

  return (
    <div className="login-container">
      {showAlert && (
        <div className="alert-overlay">
          <div className="alert-box">
            <p>Login failed. Please check your credentials and try again.</p>
            <button onClick={handleCloseAlert}>Close</button>
          </div>
        </div>
      )}
      <div className="login-box">
        <h2>Login</h2>
        <form onSubmit={handleSubmit}>
          <div className="input-group">
            <label>Email</label>
            <input 
              type="email" 
              className="form-control" 
              placeholder="Enter Your Email"
              required 
              value={email} 
              onChange={e => setEmail(e.target.value)} 
            />
          </div>
          <div className="input-group">
            <label>Password</label>
            <input 
              type="password" 
              className="form-control" 
              placeholder="Enter Your Password"
              required 
              value={password} 
              onChange={e => setPassword(e.target.value)} 
            />
          </div>
          <button type="submit" className="btn btn-success">Login</button>
        </form>
        <div className="social-login">
          <button className="social-btn google-btn" onClick={handleGoogleLogin}>
            <FaGoogle size={20} /> Sign in with Google
          </button>
          <button className="social-btn github-btn" onClick={handleGithubLogin}>
            <FaGithub size={20} /> Sign in with GitHub
          </button>
          <br></br>
          <a href="#" style={{ color: 'blue' , marginLeft: '50px'}}>
  <span>Not a part of the Infura family yet?   Sign up today </span>
</a>

        </div>
      </div>
    </div>
  );
};

export default Login;
