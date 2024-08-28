import React, { useState, useEffect } from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import './Header.css';
import '@fortawesome/fontawesome-free/css/all.min.css';

function Header() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    // Check if user is logged in
    const token = localStorage.getItem('token');
    if (token) {
      setIsLoggedIn(true);
    } else {
      setIsLoggedIn(false);
    }
  }, []);

  const handleSignOut = () => {
    // Clear token from local storage
    localStorage.removeItem('token');
    // Redirect to sign-in page
    navigate('/sign-in');
  };

  return (
    <div>
      <header className="navbar">
        <nav className="navbar-container">
          <a className="navbar-brand" href="/">
            <img src="https://cdn-icons-png.flaticon.com/512/1458/1458975.png" alt="Wallet Icon" className="navbar-icon" />
            W-TOKEN
          </a>
          <div className='navbar-nav-container'>
            <ul className="navbar-nav">
              <li className="nav-item">
                <NavLink className="nav-link" to="/history-gransactions">
                  <i className="fa-solid fa-scroll"></i> HIST-TRANSACTIONS
                </NavLink>
              </li>
              <li className="nav-item">
                <NavLink className="nav-link" to="/token-price">
                  <i className="fa-solid fa-ghost"></i> TOKEN-PRICE
                </NavLink>
              </li>
              <li className="nav-item">
                {isLoggedIn ? (
                  <NavLink className="nav-link" onClick={handleSignOut}>
                    <i className="fa-solid fa-right-from-bracket"></i> SIGN-OUT
                  </NavLink>
                ) : (
                  <NavLink className="nav-link" to="/sign-in">
                    <i className="fa-solid fa-right-to-bracket"></i> Sign-in
                  </NavLink>
                )}
              </li>
            </ul>
          </div>
        </nav>
      </header>
    </div>
  );
}

export default Header;
