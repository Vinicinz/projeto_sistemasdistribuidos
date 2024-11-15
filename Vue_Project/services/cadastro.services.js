import axios from 'axios'

class CadastroService {
  cadastro ( nome, data, cpf, email, password ) {
    return axios.post(`http://localhost:8080/auth/login`, {
        nome: nome,
        data: data,
        email: email,
      password: password

    });
  }
}

export default new CadastroService()






