import axios from 'axios'

class LoginService {
  login ( email, password ) {
    return axios.post(`http://localhost:8080/auth/login`, {
      email: email,
      password: password
    });
  }
  buscaLogin(userId){
    return axios.get(`http://localhost:8080/usuario/${userId}`, userId)
  }
}
export default new LoginService()






