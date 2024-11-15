<template>
  <div class="login-container">
    <h2>Login</h2>
    <form @submit.prevent="submitLogin">
      <div>
        <label for="email">Email:</label>
        <input type="text" id="email" v-model="email" required placeholder="Digite seu email" />
      </div>
      <div>
        <label for="password">Senha:</label>
        <input type="password" id="password" v-model="password" required placeholder="Digite sua senha" />
      </div>
      <div>
        <button @click="handleLogin">Login</button>
      </div>
      <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
    </form>
  </div>
</template>

<script>
import loginServices from '../../services/login.services';
import { jwtDecode } from 'jwt-decode';


export default {
  data() {
    return {
      email: '',
      password: '',
      errorMessage: '',
    };
  },

  methods: {
    async handleLogin() {
      try {
        const response = await loginServices.login(this.email, this.password);
        console.log(response.data);

        if (response.status === 200) {
          const token = response.data.token;
          localStorage.setItem('token', token);
          // Decodificar o token para pegar o ID do usuário
          const decodedToken = jwtDecode(token);
          const userId = decodedToken.userId;  // Supondo que o ID do usuário está no campo "sub" do token
          // Armazenar apenas o ID do usuário no localStorage
          localStorage.setItem('userId', userId);
          this.$router.push('/').then(() => {
            window.location.reload();
          });
        } else {
          this.errorMessage = "Erro no login, verifique suas credenciais.";
        }
      } catch (error) {
        console.error(error);
        this.errorMessage = error.response?.data?.message || "Erro ao conectar ao servidor.";
      }
    },
  },
};
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 0 auto;
  padding: 40px 30px;
  border-radius: 12px;
  background: rgba(71, 2, 182, 0.1);
  backdrop-filter: blur(10px);
  box-shadow: 0px 4px 12px rgba(15, 5, 75, 0.2);
  text-align: center;
  color: #6f05fa;
}

.profile-icon {
  border-radius: 50%;
  width: 80px;
  height: 80px;
  background: rgba(42, 5, 248, 0.2);
  padding: 10px;
  margin: 0 auto 20px;
}

.profile-icon img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}

label {
  display: block;
  text-align: left;
  margin-bottom: 5px;
  color: rgba(0, 4, 255, 0.8);
  font-size: 0.9rem;
}

input {
  width: 100%;
  padding: 10px;
  margin-bottom: 15px;
  border: none;
  border-bottom: 1px solid rgba(255, 255, 255, 0.5);
  background: transparent;
  color: #000000;
  font-size: 0.95rem;
}

input::placeholder {
  color: rgba(80, 46, 172, 0.7);
}

button {
  width: 100%;
  padding: 10px;
  background-color: #8a5fd1;
  color: #333;
  border: none;
  border-radius: 20px;
  font-weight: bold;
  cursor: pointer;
  margin-top: 10px;
}

button:hover {
  background-color: rgba(204, 156, 231, 0.9);
}

.register {
  margin-top: 20px;
}

.register a {
  color: rgba(161, 125, 230, 0.9);
  font-size: 0.9rem;
  font-weight: bold;
  text-decoration: none;
}

.register a:hover {
  text-decoration: underline;
}

.error {
  color: red;
  margin-top: 10px;
}
</style>
