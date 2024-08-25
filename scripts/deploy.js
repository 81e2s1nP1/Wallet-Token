async function main() {
    // Lấy hợp đồng từ ContractFactory
    const SimpleToken = await ethers.getContractFactory("SimpleToken");
    
    // Chỉ định số lượng token khởi tạo
    const initialSupply = ethers.utils.parseUnits("1000", 18); // 1000 token với 18 chữ số thập phân
    
    // Triển khai hợp đồng
    const simpleToken = await SimpleToken.deploy(initialSupply);
    
    // Đợi cho đến khi hợp đồng được triển khai
    await simpleToken.deployed();
    
    // In ra địa chỉ hợp đồng
    console.log("SimpleToken deployed to:", simpleToken.address);
  }
  
  main()
    .then(() => process.exit(0))
    .catch((error) => {
      console.error(error);
      process.exit(1);
    });
  