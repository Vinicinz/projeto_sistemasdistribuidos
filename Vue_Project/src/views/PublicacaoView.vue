<template>
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
      <p>{{ publicacao.texto }}</p>
    </div>
  </div>



  <div>
    <h3>Comentários</h3>
    <div class="post" v-for="comentario in comentarios" :key="comentario.id">
      <div class="post-header">
        <img src="https://via.placeholder.com/40" alt="User Profile">
        <div class="user-info"><span class="username"> {{ comentario.usuario ? comentario.usuario.login : 'Usuário' }} </span></div>
      </div>
      <div class="coment-content">{{ comentario.desc }}</div>
    </div>
  </div>

  <div class="post">
    <div v-if="isAuthenticated">
      <h3>Adicionar Comentário</h3>
      <form @submit.prevent="enviarComentario">
        <QuillEditor v-model="novoComentario.desc" ref="editor" theme="snow" @text-change="onEditorChange" />
        <hr>

        <button class="botao-criar" type="submit">Enviar</button>
      </form>
    </div>
    <div v-else><router-link to="/login">Faça login para comentar</router-link></div>
  </div>
</template>

<script>
import Verify from '@/components/icons/verify.vue';
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css';
import PublicacaoService from '../../services/PublicacaoService';
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
      const publicacaoResponse = await PublicacaoService.getPublicacaoUnica(publicacaoId);
      this.publicacao = publicacaoResponse.data;

      // Buscar comentários pelo ID da publicação
      const comentariosResponse = await PublicacaoService.getComentarios(publicacaoId);
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

        const response = await PublicacaoService.enviarComentario(this.novoComentario);
        window.location.reload();


      } catch (error) {
        console.error('Erro ao enviar comentário:', error);
      }
    }
  }
};
</script>