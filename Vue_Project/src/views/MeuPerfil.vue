<template>
  <div class="perfil-container">
    <h1>Meu Perfil</h1>

    <!-- Foto de perfil e login -->
    <div class="foto-perfil" v-if="login">
      <img :src="login.profilePicture || 'https://via.placeholder.com/120'" alt="Foto de perfil" />
      <h2>{{ login ? login.login : 'usuário' }}</h2> <!-- Exibe o login do usuário -->
    </div>

    <hr class="divisao" />


    <!-- Lista de publicações -->
    <div class="meus-posts">
      <h2>Minhas Publicações</h2>
      <div v-if="pub.length">
        <div v-for="post in pub" :key="post.id" class="post">
          <h3>{{ post.title }}</h3>
          <p>{{ post.content }}</p>
          <div class="interacoes">
            <span>Publicado em: {{ formatarData(post.date) }}</span>
          </div>
        </div>
      </div>
      <p v-else>Você ainda não possui publicações.</p>
    </div>
  </div>

  <div>
    <div v-if="isAuthenticated"><button @click="logout">Clique aqui para Sair</button></div>
  </div>

</template>
<script>

import loginServices from "../../services/login.services";
import PublicacaoService from "../../services/PublicacaoService";
import { isAuthenticated } from '@/router/auth';

export default {
  name: "MeuPerfil",
  data() {
    return {
      login: null, // Dados do usuário com login
      userId: localStorage.getItem("userId") || "",
      pub: [], // Publicações do usuário
    };
  },
  computed: {
    isAuthenticated() {
      return isAuthenticated();
    },
  },

  async mounted() {
    this.carregarPerfil();
  },

  methods: {
    logout() {
      localStorage.removeItem('token');
      localStorage.removeItem('userId')
      this.$router.push('/login').then(() => {
        window.location.reload();
      });

    },

    async carregarPerfil() {
      try {
        // Buscar os dados do usuário
        const response = await loginServices.buscaLogin(this.userId);
        console.log("Dados do usuário:", response.data); // Verifique se login está presente
        this.login = response.data; // Armazena os dados retornados, incluindo o login

        // Buscar as publicações do usuário
        // const responsePosts = await PublicacaoService.getPublicacoes(this.userId);
        // this.pub = responsePosts.data; // Armazena as publicações
      } catch (error) {
        console.error("Erro ao carregar o perfil:", error);
        alert("Erro ao carregar os dados do perfil.");
      }
    },
    formatarData(data) {
      const date = new Date(data);
      return date.toLocaleDateString("pt-BR", {
        day: "2-digit",
        month: "long",
        year: "numeric",
      });
    },
  },
};

</script>
<style scoped>
/* Container Principal */
.perfil-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  border: 1px solid #ddd;
  font-family: 'Roboto', sans-serif;
}

/* Título Principal */
h1 {
  text-align: center;
  font-size: 2em;
  color: #333;
  margin-bottom: 30px;
  border-bottom: 2px solid #8b0909;
  padding-bottom: 10px;
}

button {
  margin: 1rem;
  background-color: #8b0909;
  color: white;
  padding: 10px 20px;
  border-radius: 20px;
  font-weight: bold;
  font-size: 1em;
  border: none;
  cursor: pointer;
  transition: all 0.3s;
}

/* Foto de Perfil e Nome */
.foto-perfil {
  text-align: center;
  margin-bottom: 20px;
}

.foto-perfil img {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  object-fit: cover;
  border: 3px solid #8b0909;
  margin-bottom: 10px;
}

.foto-perfil h2 {
  font-size: 1.8em;
  color: #8b0909;
  margin: 0;
}

/* Divisória */
.divisao {
  border: none;
  border-top: 2px solid #ddd;
  margin: 20px 0;
}

/* Minhas Publicações */
.meus-posts {
  margin-top: 20px;
}

.meus-posts h2 {
  font-size: 1.6em;
  color: #333;
  margin-bottom: 15px;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
}

.post {
  background-color: #ffffff;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 20px;
  margin-bottom: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
}

.post:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.post h3 {
  font-size: 1.4em;
  color: #8b0909;
  margin-bottom: 10px;
}

.post p {
  font-size: 1em;
  color: #555;
  line-height: 1.6;
  margin-bottom: 15px;
}

.interacoes {
  margin-top: 10px;
  font-size: 0.9em;
  color: #666;
  border-top: 1px solid #eee;
  padding-top: 10px;
  font-style: italic;
}

/* Responsividade */
@media (max-width: 768px) {
  .perfil-container {
    padding: 15px;
  }

  h1 {
    font-size: 1.6em;
  }

  .foto-perfil h2 {
    font-size: 1.4em;
  }
}

/* Container para o botão e mensagem */
.auth-container {
  display: flex;
  align-items: center;
  gap: 10px;
  font-family: 'Roboto', sans-serif;
  font-size: 1.2em;
  color: #333;
  margin-bottom: 20px;
}

/* Estilo do botão "Sair" */
.logout-button {
  background-color: #8b0909;
  color: #fff;
  border: none;
  padding: 8px 15px;
  border-radius: 20px;
  font-weight: bold;
  font-size: 1em;
  cursor: pointer;
  transition: all 0.3s ease;
}

.logout-button:hover {
  background-color: #a05645;
  transform: scale(1.05);
}

.logout-button:active {
  background-color: #6d0707;
  transform: scale(1);
}

.logout-button:focus {
  outline: 2px solid #8b0909;
  outline-offset: 2px;
}
</style>