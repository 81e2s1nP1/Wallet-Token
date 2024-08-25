require('@nomiclabs/hardhat-ethers');
require('dotenv').config();

const { INFURA_URL, PRIVATE_KEY } = process.env;

task("accounts", "Prints the list of accounts", async (taskArgs, hre) => {
  const accounts = await hre.ethers.getSigners();

  for (const account of accounts) {
    console.log(account.address);
  }
});
  
module.exports = {
  solidity: "0.8.20",
  networks: {
    sepolia: {
      url: `${INFURA_URL}`,
      accounts: [`0x${PRIVATE_KEY}`]
    }
  }
};
console.log(`INFURA_URL: ${INFURA_URL}`);
console.log(`PRIVATE_KEY: ${PRIVATE_KEY}`);