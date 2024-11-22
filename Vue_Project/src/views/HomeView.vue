<!-- Componente central do site, onde aparecem as publicações -->
<template>
  <div class="mydict">
    <div>
      <label><input type="radio" name="radio" checked=""><span>
          <Clock /> Novo
        </span></label>
      <label><input type="radio" name="radio"><span><arrow-up /> Top</span></label>
      <label> <input type="radio" name="radio"><span>
          <Fire /> Do Momento
        </span></label>
    </div>
    <div>
      <button class="botao-criar" name="radio"><a href="/criarPub">Nova Publicação</a></button>
    </div>
  </div>
  <div class="post-container">
    <div class="post" v-for="publicacao in publicacoes" :key="publicacao.id">
      <router-link :to="`/publicacao/${publicacao.id}`">
        <div class="post-header">
          <img src="https://via.placeholder.com/40" alt="User Profile">
          <div class="user-info">
            <span class="username"> {{ publicacao.usuario ? publicacao.usuario.login : 'Usuário' }} </span>
            <span class="time"> {{ new Date(publicacao.dataPublicacao).toLocaleDateString() }}</span>
          </div>
        </div>
        <div class="post-content">
          <h4>{{ publicacao.titulo }}
            <span class="tooltip-container" v-if="publicacao.verificacao === true">
              <Verify /><span class="tooltip-text">Publicação Verificada!</span>
            </span>
            <span v-else> </span>
          </h4>
          <p>{{ publicacao.texto }}</p>
        </div>
      </router-link>
      <div class="post-footer">
        <div>
          <button class="gostei-btn" @click="toggleAvaliacao(publicacao.id)">Gostei</button>
        </div>
        <div class="post-footer-numbers">
          <span>{{ totalAvaliacoes[publicacao.id] || 0 }} </span>
          <span>
            <ArrowComent />
          </span>
          <span> 5 </span>
          <span>
            <ComentarioIcone />
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ArrowUp from '@/components/icons/arrow-up.vue';
import Clock from '@/components/icons/clock.vue';
import Fire from '@/components/icons/fire.vue';
import Verify from '@/components/icons/verify.vue';
import comentarioIcone from '@/components/icons/comentario-icone.vue';
import arrowComent from '@/components/icons/arrow-coment.vue';
import PublicacaoService from '../../services/PublicacaoService';
import AvaliacaoServices from '../../services/AvaliacaoServices';
import ComentarioIcone from '@/components/icons/comentario-icone.vue';
import ArrowComent from '@/components/icons/arrow-coment.vue';

export default {
  components: {
    Clock,
    ArrowUp,
    Fire,
    Verify,
    ArrowComent,
    ComentarioIcone
  },
  // armazenando as publicações aqui para serem exibidas 
  data() {
    return {
      publicacoes: [],
      totalAvaliacoes: {}
    };
  },

  async mounted() {
    try {
      const responsePublicacao = await PublicacaoService.getPublicacoes();
      this.publicacoes = responsePublicacao.data;

      // Para cada publicação, busca o total de avaliações
      this.publicacoes.forEach(publicacao => {
        this.buscarTotalAvaliacoes(publicacao.id);
      });
    } catch (error) {
      console.error("Erro ao buscar dados:", error);
    }
  },
  methods: {
    async buscarTotalAvaliacoes(publicacaoId) {
      try {
        const responseAvaliacao = await AvaliacaoServices.getAvaliacao(publicacaoId);
        const total = responseAvaliacao.data;
        this.totalAvaliacoes = {
          ...this.totalAvaliacoes,
          [publicacaoId]: total
        };
      } catch (error) {
        console.error("Erro na requisição:", error);
      }
    },
    async toggleAvaliacao(publicacaoId) {
      const userId = localStorage.getItem('userId');
      const avaliacaoTogle = {
        id: "",
        react: 1,
        usuario: {
          id: userId,
        },
        publicacao: {
          id: publicacaoId
        }
      };
      try {
        await AvaliacaoServices.postAvaliacao(avaliacaoTogle);
        await new Promise(resolve => setTimeout(resolve, 200)); // Aguarda 200ms
        await this.buscarTotalAvaliacoes(publicacaoId);
      } catch (error) {
        console.error("Erro ao curtir/descurtir publicação:", error);
      }
    },
  },
};
</script>