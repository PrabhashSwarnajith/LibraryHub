import axios from "axios";

const BASE_URL = "http://localhost:8082/api";

class AuthService {

    static async login(email, password) {
        try {
            const response = await axios.post(`${BASE_URL}/auth/login`, { email, password });
            return response.data;
        } catch (error) {
            console.error(error);
            }
    }

    static async signup(formData) {  // Corrected "singup" to "signup"
        try {
            const response = await axios.post(`${BASE_URL}/auth/register`,formData);
            return response.data;
        } catch (error) {
            if (error.response) {
                throw error.response.data; // Throw backend response error
              } else {
                throw { message: "Network error or server is unavailable." };
              }
        }
    }

}

export default AuthService;
