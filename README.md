# Wallet Token

## Mô tả

Wallet Token là một ứng dụng cho phép người dùng gửi và nhận token ERC-20 trên mạng Ethereum. Ứng dụng cung cấp các chức năng chính như:

- Kiểm tra số dư của token
- Gửi token đến địa chỉ khác
- Xem lịch sử giao dịch

## Công nghệ

- **Blockchain**: Ethereum (testnet: Sepolia)
- **Smart Contract**: Solidity ERC-20
- **Front-end**: React.js
- **Web3 Library**: Ethers.js để kết nối với blockchain
- **Dev Environment**: Hardhat, MetaMask để tương tác với dApp

## Cài đặt và Sử dụng

### Cài đặt

1. **Cài đặt Node.js và npm/yarn**: Đảm bảo rằng bạn đã cài đặt Node.js và npm hoặc yarn. Bạn có thể tải Node.js từ [nodejs.org](https://nodejs.org/) và npm sẽ được cài đặt kèm theo Node.js.

2. **Cài đặt Hardhat**: Nếu bạn chưa cài đặt Hardhat, bạn có thể cài đặt nó bằng cách sử dụng npm:

    ```bash
    npm install --save-dev hardhat
    ```

3. **Cài đặt các phụ thuộc**: Trong thư mục gốc của dự án, chạy lệnh sau để cài đặt các phụ thuộc:

    ```bash
    npm install
    ```

### Cấu hình

1. **MetaMask**: Đảm bảo rằng bạn đã cài đặt MetaMask và đã kết nối với mạng Sepolia. Bạn có thể lấy ETH từ faucet Sepolia để sử dụng trong dApp.

2. **Cấu hình Hardhat**: Đảm bảo rằng bạn đã cấu hình Hardhat để tương tác với mạng Sepolia. Tham khảo tài liệu Hardhat để biết thêm chi tiết về cấu hình mạng.

### Chạy Ứng Dụng

1. **Khởi động máy chủ phát triển**: Chạy lệnh sau để khởi động máy chủ phát triển cho ứng dụng React:

    ```bash
    npm start
    ```

    Máy chủ phát triển sẽ chạy trên `http://localhost:3000` theo mặc định.

2. **Triển khai Smart Contract**: Nếu bạn cần triển khai smart contract của mình, sử dụng Hardhat để biên dịch và triển khai smart contract lên mạng Sepolia:

    ```bash
    npx hardhat compile
    npx hardhat run scripts/deploy.js --network sepolia
    ```

## Hướng Dẫn Sử Dụng

- **Kiểm tra số dư**: Bạn có thể kiểm tra số dư của token ERC-20 trong giao diện chính của ứng dụng.
- **Gửi token**: Nhập địa chỉ và số lượng token bạn muốn gửi và thực hiện giao dịch.
- **Xem lịch sử giao dịch**: Xem lịch sử giao dịch của bạn trên giao diện ứng dụng.

## Tài liệu

- [Ethers.js Documentation](https://docs.ethers.io/v5/)
- [Hardhat Documentation](https://hardhat.org/getting-started/)
- [MetaMask Documentation](https://metamask.io/)

## Đóng góp

Nếu bạn muốn đóng góp vào dự án, vui lòng tạo một pull request hoặc mở một issue trên GitHub để thảo luận về các thay đổi hoặc cải tiến.

