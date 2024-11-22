<!-- esse cara ainda esta uma bagunça, mas vamos ajeitar e vai dar tudo certo! -->


<template>
  <!-- Template inicial de uma publicação exibindo o titulo, o conteudo e os comentarios  -->
  <div class="post">
    <div class="post-header">
      <img src="https://via.placeholder.com/40" alt="User Profile">
      <div class="user-info">
        <span class="username"> {{ publicacao.usuario ? publicacao.usuario.login : 'Usuário' }} </span>
        <span class="time"> {{ new Date(publicacao.dataPublicacao).toLocaleDateString() }}</span>
      </div>
    </div>
    
    <div class="post-content">
      <h2>{{ publicacao.titulo }}
        <span class="tooltip-container" v-if="publicacao.verificacao === true">
          <Verify /><span class="tooltip-text">Publicação Verificada!</span>
        </span>
        <span v-else> </span>
      </h2>
    </div>
    <p>{{ publicacao.texto }}</p> 
    
    <br>
    <br>

    <div>
      <div v-if="isAuthenticated">
        <h3>Adicionar Comentário</h3>
        <form @submit.prevent="enviarComentario">
          <QuillEditor
                    theme="snow"
                    v-model:content="enviarComentario.comentarios" contentType="text"/>             
                       <button type="submit">Enviar</button>
        </form>
      </div>
      
      <div v-else><router-link to="/login">Faça login para comentar</router-link></div>
    </div>

    <br>


    <!-- Loop pra chamar os comentarios -->
    <h3>Comentários</h3>
    <ul>
      <li v-for="comentario in comentarios" :key="comentario.id">
        {{ comentario.desc }} - <strong>{{ comentario.usuario.login }}</strong>
      </li>
    </ul>

    <!-- Adicionando comentarios (Rever essa parte ainda) -->

  </div>

</template>

<script>
// Armazenando dados da API
import Verify from '@/components/icons/verify.vue';
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css';
import artigoServices from '../../services/artigo.services';
import { isAuthenticated } from '@/router/auth';

export default {
  components: {
    Verify, QuillEditor
  },
  computed: {
    isAuthenticated() {
      return isAuthenticated();
    }
  },
  data() {
    return {
      publicacao: {},
      comentarios: [],
      novoComentario: {
        desc: '',
        usuario: {
          id: '5',
          role: 1
        }
        
      }
    };
  },

  // Chamada da publicação e dos comentarios pelo Id das publicações 
  async mounted() {
    const publicacaoId = this.$route.params.id;
    console.log(publicacaoId)

    try {
      // Buscar publicação pelo ID
      const publicacaoResponse = await artigoServices.getArtigo(publicacaoId);
      this.publicacao = publicacaoResponse.data;

      // Buscar comentários pelo ID da publicação
      const comentariosResponse = await artigoServices.getComentarios(publicacaoId);
      this.comentarios = comentariosResponse.data;
    } catch (error) {
      console.error('Erro ao buscar dados:', error);
    }
  },

  // Metodo para enviar comentario, ele armazena o id da publicação e cria uma desc baseado 
  // no que o usuario digitar no textarea, 
  methods: {
    // Método para enviar comentário
    async enviarComentario() {
      console.log("botao")
      const publicacaoId = this.$route.params.id;

      const comentarioPayload = {
        ...this.novoComentario,
        publicacao: { id: publicacaoId }
      };

      try {
        const response = await artigoServices.enviarComentario(comentarioPayload);
        this.comentarios.push(response.data); // Adiciona o novo comentário na lista
        this.novoComentario.desc = ''; // Limpa o campo de texto
      } catch (error) {
        console.error('Erro ao enviar comentário:', error);
      }
    }
  }
};
</script>

<style scoped>

/* Estilo Geral */
body {
  font-family: Arial, sans-serif;
  background-color: #f0f2f5;
  margin: 0;
  padding: 0;
}

/* Container Principal */
.post {
  background: #fff;
  border-radius: 10px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  padding: 20px;
  margin: 20px auto;
  max-width: 600px;
}

/* Cabeçalho da Publicação */
.post-header {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}

.post-header img {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  margin-right: 10px;
  border: 1px solid #ccc;
}

.user-info {
  display: flex;
  flex-direction: column;
}

.username {
  font-weight: bold;
  color: #333;
}

.time {
  font-size: 0.9em;
  color: #888;
}

/* Conteúdo da Publicação */
.post-content h2 {
  font-size: 1.5em;
  margin: 0 0 10px;
  display: flex;
  align-items: center;
}

.post-content p {
  font-size: 1em;
  color: #555;
  line-height: 1.6;
}

/* Tooltip para publicação verificada */
.tooltip-container {
  margin-left: 10px;
  position: relative;
}

.tooltip-text {
  display: none;
  position: absolute;
  top: 100%;
  left: 50%;
  transform: translateX(-50%);
  background: #000;
  color: #fff;
  padding: 5px 10px;
  border-radius: 5px;
  font-size: 0.8em;
  white-space: nowrap;
}

.tooltip-container:hover .tooltip-text {
  display: block;
}

/* Comentários */
h3 {
  border-bottom: 2px solid #ddd;
  padding-bottom: 5px;
  margin-top: 20px;
  font-size: 1.2em;
  color: #444;
}

ul {
  list-style: none;
  padding: 0;
}

li {
  background: #f9f9f9;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 10px;
  margin: 10px 0;
  display: flex;
  align-items: center;
}

li strong {
  margin-left: auto;
  font-weight: bold;
  color: #555;
}

li:hover {
  background: #f0f8ff;
  border-color: #8b0909;
}

/* Formulário de Comentários */
form {
  display: flex;
  flex-direction: column;
}

form textarea {
  width: 100%;
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 10px;
  margin: 10px 0;
  font-size: 1em;
}

form button {
  align-self: flex-end;
  background: #8b0909;
  color: #fff;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  cursor: pointer;
  font-size: 1em;
}

form button:hover {
  background: #8b0909;
}

/* Links */
a {
  color: #007bff;
  text-decoration: none;
}

a:hover {
  text-decoration: underline;
}

</style>