import axios from "axios";

 const BASE_URL = "http://localhost:8082/api"

class AuthService{

    static async login(username, password){
        try{
            const response = await axios.post(`${BASE_URL}/auth/login`,{username, password});
           return response.data; 
        }catch(error){
            console.error(error);
            // throw error;
        }
    }

    static async singup(){
        try{
            const response = await axios.post(`${BASE_URL}/auth/singup`,{ user});
           return response.data; 
        }catch(error){
            console.error(error);
            // throw error;
        }
    }

}
export default AuthService;