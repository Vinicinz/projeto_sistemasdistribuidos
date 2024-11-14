import axios from 'axios'

class LoginService {
  login ( email, password ) {
    return axios.post(`http://localhost:8080/auth/login`, {
      email: email,
      password: password
    });
  }
}

export default new LoginService()






