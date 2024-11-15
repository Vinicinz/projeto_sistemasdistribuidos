import axios from 'axios'

class CadastroServices {
  cadastro ( email, login, password, role ) {
    return axios.post(`http://localhost:8080/auth/register`, {
        login: login,
        email: email,
        password: password,
        role: role

    });
  }
}

export default new CadastroServices()






