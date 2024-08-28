import React, { useState, useEffect } from 'react';
import Web3 from 'web3';
import { contractAddress, contractABI } from '../contractData.js';
import axios from 'axios';
import './Wallet.css';

function Wallet({ account }) {
  const [balance, setBalance] = useState(0);
  const [recipient, setRecipient] = useState('');
  const [amount, setAmount] = useState('');
  const [contract, setContract] = useState(null);
  const [web3, setWeb3] = useState(null);
  const [tokenPrice, setTokenPrice] = useState(null);
  const [showModal, setShowModal] = useState(false); // State để kiểm soát modal
  const [modalMessage, setModalMessage] = useState(''); // Thông điệp hiển thị trong modal

  useEffect(() => {
    const initWeb3 = async () => {
      try {
        const web3Instance = new Web3(window.ethereum);
        setWeb3(web3Instance);

        const contractInstance = new web3Instance.eth.Contract(contractABI, contractAddress);
        setContract(contractInstance);

        // Get token price
        const price = await getTokenPrice(contractAddress);
        if (price) {
          setTokenPrice(price);
        }
      } catch (error) {
        console.error('Initialization failed:', error);
      }
    };

    initWeb3();
  }, []);

  useEffect(() => {
    const loadBalance = async () => {
      if (contract && account) {
        try {
          const balance = await contract.methods.balanceOf(account).call();
          setBalance(web3.utils.fromWei(balance, 'ether'));
        } catch (error) {
          console.error('Error loading balance:', error);
        }
      }
    };

    loadBalance();
  }, [contract, account, web3]);

  const handleSend = async () => {
    if (web3 && contract && recipient && amount) {
      if (!web3.utils.isAddress(recipient)) {
        setModalMessage('Invalid recipient address');
        setShowModal(true);
        return;
      }

      if (isNaN(amount) || Number(amount) <= 0) {
        setModalMessage('Invalid amount');
        setShowModal(true);
        return;
      }

      try {
        const amountInWei = web3.utils.toWei(amount, 'ether');
        await contract.methods
          .transfer(recipient, amountInWei)
          .send({ from: account });

        setModalMessage(`Successfully sent ${amount} tokens to ${recipient}!`);
        setShowModal(true);
      } catch (error) {
        console.error('Token sending failed:', error);
        setModalMessage('Token sending failed. Please check the console for details.');
        setShowModal(true);
      }
    } else {
      setModalMessage('Please ensure all fields are filled and try again.');
      setShowModal(true);
    }
  };

  const handleShowAddress = () => {
    setModalMessage(`${account}`);
    setShowModal(true);
  };

  const getTokenPrice = async (tokenAddress) => {
    try {
      const response = await axios.get(`https://api.coingecko.com/api/v3/simple/token_price/ethereum?contract_addresses=${tokenAddress.toLowerCase()}&vs_currencies=usd`);
      const priceData = response.data[tokenAddress.toLowerCase()];

      if (priceData && priceData.usd) {
        return priceData.usd;
      } else {
        console.warn('Token price data not found for the specified address.');
        return null;
      }
    } catch (error) {
      console.warn('Error fetching token price:', error);
      return null;
    }
  };

  return (
    <div className='container'>
      <h1>TOKEN SENDING</h1>
      <h3>amount of tokens: {balance} <img src="https://cdn-icons-png.flaticon.com/512/2165/2165640.png" alt="Wallet Icon" className="navbar-icon" /></h3>
      {tokenPrice && <h3>Token Price: ${tokenPrice}</h3>}
      <div className='input-size'> 
        <input
          type="text"
          placeholder="Delivery address"
          value={recipient}
          onChange={(e) => setRecipient(e.target.value)}
        />
        <input
          type="text"
          placeholder="Number of tokens"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
        />
        <div>
          <button onClick={handleShowAddress} className='btn-size-addr'>
            Show Address
          </button>
          <button onClick={handleSend} className='btn-size-send'>Send Token</button>
        </div>
      </div>

      {/* Modal for showing messages */}
      {showModal && (
        <div className="modal-overlay">
          <div className="modal">
            <p>{modalMessage}</p>
            <button onClick={() => setShowModal(false)} className='close-btn'>Close</button>
          </div>
        </div>
      )}
    </div>
  );
}

export default Wallet;
