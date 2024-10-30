
import axios from 'axios'

class AuthService {
  async ValidateToken ( token ) {
     const login = await axios.post(`http://localhost:8080/publicacao/`, token)
     return login
  }
}

export default new AuthService()
