async function main() {
    const SimpleToken = await ethers.getContractFactory("SimpleToken");
    const initialSupply = ethers.utils.parseUnits("1000", 18); // 1000 token với 18 chữ số thập phân
    const simpleToken = await SimpleToken.deploy(initialSupply);
    await simpleToken.deployed();
    console.log("SimpleToken deployed to:", simpleToken.address);
  }
  
  main()
    .then(() => process.exit(0))
    .catch((error) => {
      console.error(error);
      process.exit(1);
    });
  