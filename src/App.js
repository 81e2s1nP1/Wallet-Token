import './App.css';
import React, { useState, useEffect } from 'react';
import Web3 from 'web3';
import Wallet from './components/Wallet';
import HistoryTransactions from './components/HistoryTransactions';
import TokenPrice from './components/TokenPrice'; 
import { Route, Routes, Navigate, useLocation } from 'react-router-dom';
import { contractABI, contractAddress } from './contractData';
import Header from './components/Header';
import Footer from './components/Footer';
import Login from './components/Login';

function App() {
  const [web3, setWeb3] = useState(null);
  const [account, setAccount] = useState('');
  const [contract, setContract] = useState(null);
  const location = useLocation();
  const token = localStorage.getItem('token');
  const isLoggedIn = !!token;

  useEffect(() => {
    const loadWeb3 = async () => {
      if (window.ethereum) {
        const web3Instance = new Web3(window.ethereum);
        try {
          await window.ethereum.request({ method: 'eth_requestAccounts' });
          setWeb3(web3Instance);

          const accounts = await web3Instance.eth.getAccounts();
          setAccount(accounts[0]);

          const contractInstance = new web3Instance.eth.Contract(contractABI, contractAddress);
          setContract(contractInstance);
        } catch (error) {
          console.error('You need to grant wallet access to continue.', error);
        }
      } else {
        alert('You need to install Metamask to use this dApp.');
      }
    };

    loadWeb3();
  }, []);

  // Determine whether to show the header based on the current location
  const showHeader = location.pathname !== '/sign-in';

  return (
    <div className="app-container">
      {showHeader && <Header />}
      <div className="main-content">
        <Routes>
          <Route 
            path="/" 
            element={isLoggedIn ? (account ? <Wallet account={account} contract={contract} web3={web3} /> : <div className="loading-container"><p id="connect-state">Connecting to wallet......</p><div className="loading-bar"></div></div>) : <Navigate to="/sign-in" />} 
          />
          <Route path="/token-price" element={isLoggedIn ? <TokenPrice /> : <Navigate to="/sign-in" />} />
          <Route path="/history-gransactions" element={isLoggedIn ? <HistoryTransactions /> : <Navigate to="/history-gransactions" />} />
          <Route path="/sign-in" element={<Login />} />
        </Routes>
      </div>
      <Footer />
    </div>
  );
}

export default App;
