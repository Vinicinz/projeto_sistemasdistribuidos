<template>
    <div class="perfil-container">
      <h1>Meu Perfil</h1>
  
      <!-- Seção de Foto de Perfil -->
      <div class="foto-perfil">
        <img :src="user.fotoPerfil || defaultPhoto" alt="Foto de perfil" />
        <input type="file" @change="handlePhotoChange" />
        <button @click="savePhoto" :disabled="loadingPhoto">Alterar Foto</button>
      </div>
  
      <!-- Seção de Dados do Usuário -->
      <form @submit.prevent="updateProfile">
        <div class="form-group">
          <label for="nome">Nome</label>
          <input
            type="text"
            id="nome"
            v-model="user.nome"
            :disabled="!isEditing"
            required
          />
        </div>
  
        <div class="form-group">
          <label for="email">Email</label>
          <input
            type="email"
            id="email"
            v-model="user.email"
            :disabled="!isEditing"
            required
          />
        </div>
  
        <div class="actions">
          <button type="button" @click="toggleEdit" v-if="!isEditing">Editar</button>
          <button type="submit" v-if="isEditing">Salvar Alterações</button>
          <button type="button" @click="cancelEdit" v-if="isEditing">Cancelar</button>
        </div>
      </form>
  
      <!-- Seção de Publicação -->
      <div class="nova-publicacao">
        <h2>Nova Publicação</h2>
                    <QuillEditor theme="snow" />

        <button @click="publishPost" :disabled="loadingPost || !newPost.trim()">Publicar</button>
      </div>
  
      <!-- Seção de Posts -->
      <div class="meus-posts">
        <h2>Minhas Publicações</h2>
        <div v-for="post in posts" :key="post.id" class="post">
          <p>{{ post.conteudo }}</p>
          <div class="interacoes">
            <span>{{ post.curtidas }} Curtidas</span>
            <span>{{ post.comentarios.length }} Comentários</span>
          </div>
        </div>
      </div>
    </div>

    <div>
      <div v-if="isAuthenticated">Bem-vindo! <button @click="logout">Sair</button></div>
      <div v-else><router-link to="/login">Faça login</router-link></div>
    </div>


  </template>
  
  <script>
  import axios from "axios";
  import { QuillEditor } from '@vueup/vue-quill';
  import '@vueup/vue-quill/dist/vue-quill.snow.css';
  import { isAuthenticated } from '@/router/auth';


  
  export default {
    computed: {
      isAuthenticated() {
        return isAuthenticated();
      }
    },
    components: {
     QuillEditor
  },
    data() {
      return {
        user: {
          nome: "",
          email: "",
          fotoPerfil: "",
        },
        originalUserData: {}, // Para restaurar os dados ao cancelar edição
        posts: [],
        newPost: "",
        defaultPhoto: "https://via.placeholder.com/150", // Foto padrão
        isEditing: false, // Estado de edição
        loadingPhoto: false,
        loadingProfile: false,
        loadingPost: false,
      };
    },
    created() {
      this.fetchProfile();
      this.fetchPosts();
    },
    methods: {
      async fetchProfile() {
        try {
          const response = await axios.get("http://localhost:3000/api/usuario");
          this.user = response.data;
          this.originalUserData = { ...response.data }; // Salva os dados originais
        } catch (error) {
          console.error("Erro ao carregar o perfil:", error);
        }
      },
      async updateProfile() {
        this.loadingProfile = true;
        try {
          await axios.put("http://localhost:3000/api/usuario", this.user);
          alert("Perfil atualizado com sucesso!");
          this.isEditing = false; // Sai do modo de edição
          this.originalUserData = { ...this.user }; // Atualiza os dados originais
        } catch (error) {
          console.error("Erro ao atualizar perfil:", error);
          alert("Erro ao atualizar perfil.");
        } finally {
          this.loadingProfile = false;
        }
      },
      toggleEdit() {
        this.isEditing = true; // Ativa o modo de edição
      },
      cancelEdit() {
        this.user = { ...this.originalUserData }; // Restaura os dados originais
        this.isEditing = false; // Sai do modo de edição
      },
      async handlePhotoChange(event) {
        const file = event.target.files[0];
        if (!file) return;
        const formData = new FormData();
        formData.append("foto", file);
  
        this.loadingPhoto = true;
        try {
          const response = await axios.post(
            "http://localhost:3000/api/usuario/foto",
            formData,
            { headers: { "Content-Type": "multipart/form-data" } }
          );
          this.user.fotoPerfil = response.data.url;
          alert("Foto de perfil alterada com sucesso!");
        } catch (error) {
          console.error("Erro ao alterar foto de perfil:", error);
          alert("Erro ao alterar foto de perfil.");
        } finally {
          this.loadingPhoto = false;
        }
      },
      async fetchPosts() {
        try {
          const response = await axios.get("http://localhost:3000/api/posts");
          this.posts = response.data;
        } catch (error) {
          console.error("Erro ao carregar publicações:", error);
        }
      },
      async publishPost() {
        if (!this.newPost.trim()) return;
  
        this.loadingPost = true;
        try {
          const response = await axios.post("http://localhost:3000/api/posts", {
            conteudo: this.newPost,
          });
          this.posts.unshift(response.data); // Adiciona a nova publicação na lista
          this.newPost = ""; // Limpa o campo de texto
          alert("Publicação realizada com sucesso!");
        } catch (error) {
          console.error("Erro ao publicar:", error);
          alert("Erro ao publicar.");
        } finally {
          this.loadingPost = false;
        }
      },
      logout() {
        localStorage.removeItem('token');
        localStorage.removeItem('userId')
        this.$router.push('/login');
      }
    },
  };
  </script>
  
  <style scoped>
  /* Container Principal */
  .perfil-container {
    max-width: 900px;
    margin: 0 auto;
    padding: 20px;
    background-color: #f9f9f9;
    border-radius: 8px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    font-family: 'Roboto', sans-serif;
  }
  
  /* Cabeçalho */
  h1 {
    text-align: center;
    font-size: 2.2em;
    color: #333;
    margin-bottom: 20px;
  }
  
  /* Foto de Perfil */
  .foto-perfil {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 30px;
  }
  .foto-perfil img {
    width: 120px;
    height: 120px;
    border-radius: 50%;
    object-fit: cover;
    border: 4px solid #8b0909;
    margin-bottom: 10px;
  }
  .foto-perfil input[type="file"] {
    display: none;
  }
  .foto-perfil button {
    background-color: #8b0909;
    color: white;
    border: none;
    padding: 10px 15px;
    border-radius: 20px;
    cursor: pointer;
    font-weight: bold;
    transition: background-color 0.3s;
  }
  .foto-perfil button:hover {
    background-color: #8b0909;
  }
  .foto-perfil label {
    cursor: pointer;
    color: #8b0909;
    font-weight: bold;
    margin-bottom: 10px;
  }
  
  /* Formulário */
  form {
    margin-top: 20px;
  }
  .form-group {
    margin-bottom: 20px;
  }
  label {
    display: block;
    font-size: 1.1em;
    color: #555;
    margin-bottom: 5px;
  }
  input,
  textarea {
    width: 100%;
    padding: 12px 15px;
    border: 1px solid #ddd;
    border-radius: 8px;
    box-sizing: border-box;
    font-size: 1em;
    color: #333;
    transition: border-color 0.3s;
  }
  input:disabled {
    background-color: #f0f0f0;
    cursor: not-allowed;
  }
  input:focus,
  textarea:focus {
    border-color: #8b0909;
    outline: none;
  }
  
  /* Botões */
  .actions {
    display: flex;
    justify-content: flex-start;
    gap: 15px;
  }
  button {
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
  button:hover {
    background-color: #a05645;
  }
  button:disabled {
    background-color: #ddd;
    color: #999;
    cursor: not-allowed;
  }
  
  /* Nova Publicação */
  .nova-publicacao {
    margin-top: 30px;
    padding: 20px;
    background-color: white;
    border: 1px solid #ddd;
    border-radius: 8px;
  }
  .nova-publicacao h2 {
    font-size: 1.5em;
    color: #333;
    margin-bottom: 15px;
  }
  textarea {
    margin-bottom: 15px;
  }
  textarea::placeholder {
    color: #aaa;
  }
  
  /* Minhas Publicações */
  .meus-posts {
    margin-top: 40px;
  }
  .meus-posts h2 {
    font-size: 1.8em;
    color: #333;
    margin-bottom: 15px;
  }
  .post {
    background-color: white;
    border: 1px solid #ddd;
    border-radius: 8px;
    padding: 15px;
    margin-bottom: 15px;
    transition: box-shadow 0.3s;
  }
  .post:hover {
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  }
  .post p {
    font-size: 1em;
    color: #555;
  }
  .interacoes {
    margin-top: 10px;
    font-size: 0.9em;
    color: #666;
    display: flex;
    justify-content: space-between;
    border-top: 1px solid #eee;
    padding-top: 10px;
  }
  
  /* Responsividade */
  @media (max-width: 768px) {
    .perfil-container {
      padding: 15px;
    }
    h1 {
      font-size: 1.8em;
    }
    button {
      font-size: 0.9em;
      padding: 8px 15px;
    }
    textarea {
      font-size: 0.9em;
    }
  }
  </style>
  