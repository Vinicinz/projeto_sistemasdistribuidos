<template>
    <div class="cadastro-container">
      <h1>Cadastro de Usuário</h1>
      <form @submit.prevent="submitForm">
        <div class="form-group">
          <label for="nome">Nome</label>
          <input type="text" id="nome" v-model="form.nome" required />
        </div>
  
        <div class="form-group">
          <label for="cpf">CPF</label>
          <input
            type="text"
            id="cpf"
            v-model="form.cpf"
            required
            @input="formatCPF"
            @change="validateCPF"
          />
          <span v-if="cpfError" class="error">{{ cpfError }}</span>
        </div>
  
        <div class="form-group">
          <label for="dataNascimento">Data de Nascimento</label>
          <input type="date" id="dataNascimento" v-model="form.dataNascimento" required />
        </div>
  
        <div class="form-group">
          <label for="email">Email</label>
          <input type="email" id="email" v-model="form.email" required />
        </div>

        <div>
        <label for="password">Senha</label>
        <input type="password" id="password" v-model="password" required placeholder="Digite sua senha" />
      </div>
  
        <button type="submit" :disabled="cpfError">Cadastrar</button>
      </form>
    </div>
  </template>
  
  <script>
import cadastroServices from '../../services/cadastro.services';

  export default {
    data() {
      return {
        form: {
          nome: "",
          cpf: "",
          dataNascimento: "",
          email: ""
        },
        cpfError: ""
      };
    },
    methods: {
      formatCPF() {
        let cpf = this.form.cpf.replace(/\D/g, "").slice(0, 11);
        cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2");
        cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2");
        cpf = cpf.replace(/(\d{3})(\d{1,2})$/, "$1-$2");
        this.form.cpf = cpf;
      },
      validateCPF() {
        const cpf = this.form.cpf.replace(/\D/g, "");
  
        if (cpf.length !== 11) {
          this.cpfError = "CPF deve ter 11 dígitos.";
          return false;
        }
  
        if (/^(\d)\1+$/.test(cpf)) {
          this.cpfError = "CPF inválido.";
          return false;
        }
  
        // Validação CPF
        let sum = 0;
        for (let i = 0; i < 9; i++) {
          sum += parseInt(cpf.charAt(i)) * (10 - i);
        }
        let firstVerifier = (sum * 10) % 11;
        if (firstVerifier === 10) firstVerifier = 0;
        if (firstVerifier !== parseInt(cpf.charAt(9))) {
          this.cpfError = "CPF inválido.";
          return false;
        }
  
        sum = 0;
        for (let i = 0; i < 10; i++) {
          sum += parseInt(cpf.charAt(i)) * (11 - i);
        }
        let secondVerifier = (sum * 10) % 11;
        if (secondVerifier === 10) secondVerifier = 0;
        if (secondVerifier !== parseInt(cpf.charAt(10))) {
          this.cpfError = "CPF inválido.";
          return false;
        }
  
        this.cpfError = ""; 
        return true;
      },
      async submitForm() {
        if (this.cpfError) {
          alert("Por favor, corrija o CPF antes de enviar.");
          return;
        }
  
        this.loading = true;

try {
  const response = await cadastroServices("", this.form);
  alert("Cadastro realizado com sucesso!");

  // Redireciona para a página de login
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

.cadastro-container {
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
