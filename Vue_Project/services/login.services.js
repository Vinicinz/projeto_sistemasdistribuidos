import api from './api';

class LoginService {
  login ( email, password ) {
    return api.post(`/auth/login`, {
      email: email,
      password: password
    });
  }
  buscaLogin(userId){
    return api.get(`/usuario/${userId}`, userId)
  }
}
export default new LoginService()






