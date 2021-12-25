import axios from "axios";

const AuthService = (function() {

    const _signIn = async (credentials) => {

        const response = await axios.post("http://localhost:8080/api/v1/login", {
            username: credentials.username, password: credentials.password
        });
        if (response) {

        }
        return response.data;
    };


    return {
        signIn: _signIn
    };
})();

export default AuthService;