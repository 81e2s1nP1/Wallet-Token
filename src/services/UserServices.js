import axios from "axios";
import { Port } from "../Port";

const API_CREATE_USER = `http://localhost:${Port}/api/user/save`;
const API_CREATE_LOGIN = `http://localhost:${Port}/api/user/login`;

class UserService {
    create(user) {
        return axios.post(API_CREATE_USER, user);
    }

    login(login) {
        return axios.post(API_CREATE_LOGIN, login);
    }

    storeToken = (token) => localStorage.setItem("token", token);
    getToken = () => localStorage.getItem("token");

    SaveLoggerInUser(username, role) {
        sessionStorage.setItem("authenticationUser", username);
        sessionStorage.setItem("role", role);
    }

    isLoggerInUser() {
        const username = sessionStorage.getItem("authenticationUser");
        console.log("username: ", username);
        return username !== null;
    }

    getLoggerInUser() {
        return sessionStorage.getItem("authenticationUser");
    }

    logout() {
        localStorage.clear();
        sessionStorage.clear();
    }

    isAdminUser() {
        const role = sessionStorage.getItem("role");
        return role === "ROLE_ADMIN";
    }
}

const userServiceInstance = new UserService();
export default userServiceInstance;
