import axios from "axios";

const API_URL = "http://localhost:8080/auth/";

// Register user
const register = async (userData) => {
  const response = await axios.post(API_URL + "register", userData);
  return response.data;
};

// ✅ Login user
const login = async (userData) => {
  const response = await axios.post(API_URL + "login", userData);
  
  if (response.data) {
    localStorage.setItem("user", JSON.stringify(response.data)); // ✅ Store user data in localStorage
  }

  return response.data;
};

// ✅ Logout user
const logout = () => {
  localStorage.removeItem("user");
};

const authService = {
  register,
  login,
  logout,
};

export default authService;
