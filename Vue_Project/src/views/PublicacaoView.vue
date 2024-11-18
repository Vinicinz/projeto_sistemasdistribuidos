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
          <QuillEditor v-model="novoComentario.desc" ref="editor" theme="snow" @text-change="onEditorChange" />
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
          id: null,
          role: '1'
        },
        publicacao: {
          id: null
        }
      }
    };
  },

  // Chamada da publicação e dos comentarios pelo Id das publicações 
  async mounted() {
    const publicacaoId = this.$route.params.id;
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

  methods: {
    onEditorChange() {
      const editorContent = this.$refs.editor.getText().trim();
      this.novoComentario.desc = editorContent || 'falha';
    },
    async enviarComentario() {
      const publicacaoId = this.$route.params.id;
      const userId = localStorage.getItem('userId');

      try {
        this.novoComentario.usuario.id = userId;
        this.novoComentario.publicacao.id = publicacaoId;

        const response = await artigoServices.enviarComentario(this.novoComentario);
        window.location.reload();


      } catch (error) {
        console.error('Erro ao enviar comentário:', error);
      }
    }
  }
};
</script>