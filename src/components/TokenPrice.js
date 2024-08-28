import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Line } from 'react-chartjs-2';
import { Chart as ChartJS, CategoryScale, LinearScale, LineElement, PointElement, Tooltip, Legend } from 'chart.js';
import './TokenPrice.css';

// Register chart components
ChartJS.register(
  CategoryScale,
  LinearScale,
  LineElement,
  PointElement,
  Tooltip,
  Legend
);

function TokenPrice() {
  const [priceData, setPriceData] = useState({ labels: [], data: [] });
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchPriceData = async () => {
      try {
        // Fetch price data from CoinGecko API
        const response = await axios.get('https://api.coingecko.com/api/v3/coins/ethereum/market_chart?vs_currency=usd&days=30');
        const prices = response.data.prices;

        // Process data for the chart
        const labels = prices.map(price => new Date(price[0]).toLocaleDateString());
        const data = prices.map(price => price[1]);

        setPriceData({
          labels,
          data
        });
        setLoading(false);
      } catch (error) {
        console.error('Error fetching price data:', error);
        setLoading(false);
      }
    };

    fetchPriceData();
  }, []);

  const chartData = {
    labels: priceData.labels,
    datasets: [
      {
        label: 'Ethereum Price (USD)',
        data: priceData.data,
        borderColor: '#4bc0c0',
        backgroundColor: 'rgba(75, 192, 192, 0.2)',
        fill: true,
      },
    ],
  };

  return (
    <div className="token-price-container">
      <h1>Ethereum Price Chart</h1>
      <h3>Current Ethereum Price: 2,720.51 USD</h3>
      {loading ? (
        <div>
          <p>Loading price data...</p>
          <div className='loading-bar'></div>
        </div>
      ) : (
        <Line data={chartData} />
      )}
    </div>
  );
}

export default TokenPrice;
