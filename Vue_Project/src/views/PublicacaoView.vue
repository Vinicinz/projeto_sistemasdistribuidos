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
    <h3>Comentários</h3>

    <!-- Loop pra chamar os comentarios -->
    <ul>
      <li v-for="comentario in comentarios" :key="comentario.id">
        {{ comentario.desc }} - <strong>{{ comentario.usuario.login }}</strong>
      </li>
    </ul>


    <div>
      <div v-if="isAuthenticated">
        <h3>Adicionar Comentário</h3>
        <form @submit.prevent="enviarComentario">
          <QuillEditor theme="snow" /> 
          <button type="submit">Enviar</button>
        </form>
      </div>
      <div v-else><router-link to="/login">Faça login para comentar</router-link></div>
    </div>

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
        const response = await ArtigosService.enviarComentario(comentarioPayload);
        this.comentarios.push(response.data); // Adiciona o novo comentário na lista
        this.novoComentario.desc = ''; // Limpa o campo de texto
      } catch (error) {
        console.error('Erro ao enviar comentário:', error);
      }
    }
  }
};
</script>