<template>
  <div class="login-container">
    <h2>Login</h2>
    <form @submit.prevent="submitLogin">
      <div>
        <label for="email">Email</label>
        <input type="text" id="email" v-model="email" required placeholder="Digite seu email" />
      </div>
      <div>
        <label for="password">Senha</label>
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
body {
  font-family: 'Arial', sans-serif;
  background: linear-gradient(to bottom right, #dcd9d9, #dedede); /* Fundo em tons de vermelho suave */
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0;
}

.login-container {
  width: 400px;
  padding: 40px;
  background: rgba(204, 204, 204, 0.95); /* Fundo claro translúcido com tom rosado */
  border-radius: 15px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1); /* Sombra suave */
  backdrop-filter: blur(8px);
  text-align: center;
  color: #8b0909; /* Texto em vermelho mais escuro */
  border: 1px solid #e3e3e3; /* Borda em vermelho clarinho */
}

.login-container h2 {
  font-size: 2rem;
  font-weight: bold;
  margin-bottom: 20px;
  color: #b71c1c; /* Título em vermelho escuro */
}

.login-container form {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.login-container label {
  display: block;
  text-align: left;
  font-size: 1rem;
  color: #b71c1c; /* Labels em vermelho escuro */
  margin-bottom: 5px;
}

.login-container input {
  width: 100%;
  padding: 12px;
  border: 1px solid #8b0909; /* Borda em vermelho claro */
  border-radius: 8px;
  background-color: rgba(236, 236, 236, 0.7); /* Fundo claro translúcido */
  color: #000000; /* Texto em vermelho */
  font-size: 1rem;
  outline: none;
  transition: border-color 0.3s ease, background-color 0.3s ease;
}

.login-container input:focus {
  border-color: #000000; /* Destaque em vermelho médio */
  background-color: rgba(254, 254, 254, 0.9); /* Fundo levemente mais claro no foco */
}

.login-container input::placeholder {
  color: rgba(118, 118, 118, 0.7); /* Placeholder em tom suave de vermelho */
  font-size: 0.9rem;
}

.login-container button {
  width: 100%;
  padding: 12px;
  background-color: #8b0909; /* Botão em vermelho médio */
  color: #fff; /* Texto branco */
  font-size: 1rem;
  font-weight: bold;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

.login-container button:hover {
  background-color: #b71c1c; /* Botão mais escuro no hover */
  transform: translateY(-2px);
}

.login-container button:active {
  transform: translateY(0);
}

.error {
  color: #d32f2f; /* Mensagem de erro em vermelho intenso */
  font-size: 0.9rem;
  margin-top: 10px;
}

@media (max-width: 480px) {
  .login-container {
    width: 90%;
    padding: 30px;
  }

  .login-container h2 {
    font-size: 1.8rem;
  }

  .login-container input {
    font-size: 0.9rem;
  }
}
</style>
