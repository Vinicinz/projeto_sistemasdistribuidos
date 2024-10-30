import axios from 'axios'

class LoginService {
  getLogin ( idLogin ) {
    return axios.get(`http://localhost:8080/login/${idLogin}`)
  }
}

export default new LoginService()