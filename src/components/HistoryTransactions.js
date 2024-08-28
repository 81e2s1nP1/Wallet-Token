// src/components/HistoryTransactions.js

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './HistoryTransactions.css';

const ETHERSCAN_API_KEY = 'M92PK82EBB7V156FSGYF462MF7TRAXMAEH'; // Replace with your API Key
const ETHERSCAN_API_URL = 'https://api-sepolia.etherscan.io/api';

function HistoryTransactions() {
  const [transactions, setTransactions] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchTransactions = async () => {
      try {
        const address = '0x82cb3d95FD846Ae08f2654c78417e0E41264D6aa';
        const response = await axios.get(`${ETHERSCAN_API_URL}?module=account&action=tokentx&address=${address}&sort=desc&apikey=${ETHERSCAN_API_KEY}`);
        console.log(response);
        setTransactions(response.data.result.slice(0, 5)); // Keep only the 5 most recent transactions
        setLoading(false);
      } catch (error) {
        setError('Error fetching transactions');
        setLoading(false);
      }
    };

    fetchTransactions();
  }, []);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div className="history-container">
      <h1>Transaction History</h1>
      <table className="history-table">
        <thead>
          <tr>
            <th>Date</th>
            <th>Transaction Hash</th>
            <th>From</th>
            <th>To</th>
            <th>Amount</th>
            <th>Status</th>
            <th>Transaction Fee</th>
            <th>Gas Price</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((tx, index) => (
            <tr key={index}>
              <td>{new Date(tx.timeStamp * 1000).toLocaleString()}</td>
              <td><a href={`https://sepolia.etherscan.io/tx/${tx.hash}`} target="_blank" rel="noopener noreferrer" style={{color:'white'}}>{tx.hash.slice(0, 6) + '...' + tx.hash.slice(-4)}</a></td>
              <td>{tx.from.slice(0, 6) + '...' + tx.from.slice(-4)}</td>
              <td>{tx.to.slice(0, 6) + '...' + tx.to.slice(-4)}</td>
              <td>{(tx.value / 1e18).toFixed(4)} STK</td>
              <td>{tx.isError === '0' ? 'Failed' : 'Success'}</td>
              <td>{(tx.gasUsed * tx.gasPrice / 1e18).toFixed(18).slice(0, 7)} ETH</td>
              <td>{(tx.gasPrice / 1e9).toFixed(9)} Gwei</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default HistoryTransactions;
