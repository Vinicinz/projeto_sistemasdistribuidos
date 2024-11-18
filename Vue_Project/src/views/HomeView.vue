<!-- Componente central do site, onde aparecem as publicações -->
<template>
  <div class="mydict">
    <div><label><input type="radio" name="radio" checked=""><span>
          <Clock /> Novo
        </span></label>
      <label><input type="radio" name="radio"><span><arrow-up /> Top</span></label>
      <label> <input type="radio" name="radio"><span>
          <Fire /> Do Momento
        </span></label>
    </div>
    <div >
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
        <div class="post-footer">
          <span>Gostei | Comentar </span>
          <span> 0 </span>
        </div>
      </router-link>
    </div>
  </div>
</template>

<!-- iniciando componentes de icones -->
<script>
import ArrowUp from '@/components/icons/arrow-up.vue';
import Clock from '@/components/icons/clock.vue';
import Fire from '@/components/icons/fire.vue';
import Verify from '@/components/icons/verify.vue';
import artigoServices from '../../services/artigo.services';

export default {
  components: {
    Clock,
    ArrowUp,
    Fire,
    Verify
  },
  // armazenando as publicações aqui para serem exibidas 
  data() {
    return {
      publicacoes: [],
    };
  },

  // Chamando o EndPoint do Back e armazenando em publicacoes
  async mounted() {
    try {
      const response = await artigoServices.getPublicacoes(); // Usando o serviço para fazer a requisição
      this.publicacoes = response.data; // Armazenando os dados no data
    } catch (error) {
      console.error('Erro ao buscar dados:', error);
    }
  },
};
</script>