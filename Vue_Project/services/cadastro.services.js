import api from './api';

class CadastroServices {
  cadastro ( email, login, password, role ) {
    return api.post(`/auth/register`, {
        login: login,
        email: email,
        password: password,
        role: role

    });
  }
}

export default new CadastroServices()






