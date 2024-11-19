<template>
  <div class="container-container">
    <div class="cadastro-container">
      <h2>Cadastro de Usuário</h2>
      <form @submit.prevent="submitForm">
        <div>
          <label for="nome">Usuário</label>
          <input type="text" id="nome" v-model="login" required placeholder="Digite seu nome de usuário" />
        </div>

        <div>
          <label for="email">Email</label>
          <input type="email" id="email" v-model="email" required placeholder="Digite seu email" />
        </div>

        <div>
          <label for="password">Senha</label>
          <input type="password" id="password" v-model="password" required placeholder="Digite sua senha" />
        </div>

        <button @click="cadastrar">Cadastrar</button>
      </form>
    </div>
  </div>
</template>

<script>
import cadastroServices from '../../services/cadastro.services';

export default {
  data() {
    return {

      login: "",
      email: "",
      password: "",
      role: 1

    };
  },
  methods: {
    //   formatCPF() {
    //     let cpf = this.form.cpf.replace(/\D/g, "").slice(0, 11);
    //     cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2");
    //     cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2");
    //     cpf = cpf.replace(/(\d{3})(\d{1,2})$/, "$1-$2");
    //     this.form.cpf = cpf;
    //   },
    //   validateCPF() {
    //     const cpf = this.form.cpf.replace(/\D/g, "");

    //     if (cpf.length !== 11) {
    //       this.cpfError = "CPF deve ter 11 dígitos.";
    //       return false;
    //     }

    //     if (/^(\d)\1+$/.test(cpf)) {
    //       this.cpfError = "CPF inválido.";
    //       return false;
    //     }

    //     // Validação CPF
    //     let sum = 0;
    //     for (let i = 0; i < 9; i++) {
    //       sum += parseInt(cpf.charAt(i)) * (10 - i);
    //     }
    //     let firstVerifier = (sum * 10) % 11;
    //     if (firstVerifier === 10) firstVerifier = 0;
    //     if (firstVerifier !== parseInt(cpf.charAt(9))) {
    //       this.cpfError = "CPF inválido.";
    //       return false;
    //     }

    //     sum = 0;
    //     for (let i = 0; i < 10; i++) {
    //       sum += parseInt(cpf.charAt(i)) * (11 - i);
    //     }
    //     let secondVerifier = (sum * 10) % 11;
    //     if (secondVerifier === 10) secondVerifier = 0;
    //     if (secondVerifier !== parseInt(cpf.charAt(10))) {
    //       this.cpfError = "CPF inválido.";
    //       return false;
    //     }

    //     this.cpfError = "";
    //     return true;
    //   },
    //   async submitForm() {
    //     if (this.cpfError) {
    //       alert("Por favor, corrija o CPF antes de enviar.");
    //       return;
    //     }

    async cadastrar() {
      try {
        const response = await cadastroServices.cadastro(this.email, this.login, this.password, this.role);
        console.log(response.data);
        alert("Cadastro realizado com sucesso!");

        this.$router.push("/login");
      } catch (error) {
        console.error("Erro ao cadastrar:", error.response || error);
        alert("Erro ao realizar o cadastro. Tente novamente.");
      } finally {
        this.loading = false;
      }
    }
  }
};
</script>

<style scoped>
body {
  font-family: 'Arial', sans-serif;
  background: linear-gradient(to bottom right, #dcd9d9, #dedede);
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0;
}

.container-container{
  display: flex;
  justify-content: center;
}
.cadastro-container {
  width: 400px;
  padding: 40px;
  background: rgba(204, 204, 204, 0.95);
  border-radius: 15px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  backdrop-filter: blur(8px);
  text-align: center;
  color: #8b0909;
  border: 1px solid #e3e3e3;
}

.cadastro-container h2 {
  font-size: 2rem;
  font-weight: bold;
  margin-bottom: 20px;
  color: #b71c1c;
}

.cadastro-container form {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.cadastro-container label {
  display: block;
  text-align: left;
  font-size: 1rem;
  color: #b71c1c;
  margin-bottom: 5px;
}

.cadastro-container input {
  width: 100%;
  padding: 12px;
  border: 1px solid #8b0909;
  border-radius: 8px;
  background-color: rgba(236, 236, 236, 0.7);
  color: #000000;
  font-size: 1rem;
  outline: none;
  transition: border-color 0.3s ease, background-color 0.3s ease;
}

.cadastro-container input:focus {
  border-color: #000000;
  background-color: rgba(254, 254, 254, 0.9);
}

.cadastro-container input::placeholder {
  color: rgba(118, 118, 118, 0.7);
  font-size: 0.9rem;
}

.cadastro-container button {
  width: 100%;
  padding: 12px;
  background-color: #8b0909;
  color: #fff;
  font-size: 1rem;
  font-weight: bold;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

.cadastro-container button:hover {
  background-color: #b71c1c;
  transform: translateY(-2px);
}

.cadastro-container button:active {
  transform: translateY(0);
}

.error {
  color: #d32f2f;
  font-size: 0.9rem;
  margin-top: 10px;
}

@media (max-width: 480px) {
  .cadastro-container {
    width: 90%;
    padding: 30px;
  }

  .cadastro-container h2 {
    font-size: 1.8rem;
  }

  .cadastro-container input {
    font-size: 0.9rem;
  }
}
</style>
