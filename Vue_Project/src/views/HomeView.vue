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
    <div>
      <button name="radio"><a href="/criarPub"><span> Nova Publicação</span></a></button>

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
          <button 
        @click="CurtireDescurtir" 
        :class="{ 'curtido': curtido }"
        class="botao-curtir"
        :disabled="carregando"
      >
        {{ curtido ? 'Descurtir' : 'Curtir' }}
      </button>
      <span>{{ curtidas }} {{ curtidas === 1 ? 'curtida' : 'curtidas' }}</span>
    </div>
          <span> 0 </span>
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
  name: "BotaoCurtir",
    props: {
      idPublicacao: {
        type: Number,
        required: true,
      },
      curtidas: {
        type: Number,
        required: true,
      },
      usuarioCurtiu: {
        type: Boolean,
        required: true,
      },
    },
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
        curtido: this.usuarioCurtiu, // Estado inicial de curtido
        curtidas: this.curtidasIniciais, // Número inicial de curtidas
        carregando: false, // Indica se está processando
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

<style scoped>
button[name="radio"] {
  background-color: #8b0909; /* Cor azul primária */
  border: none; /* Remove borda padrão */
  border-radius: 5px; /* Bordas arredondadas */
  padding: 10px 20px; /* Espaçamento interno */
  color: #fff; /* Texto branco */
  font-size: 1rem; /* Tamanho do texto */
  cursor: pointer; /* Cursor de clique */
  display: inline-flex; /* Para alinhar com o link */
  align-items: center; /* Centraliza verticalmente */
  justify-content: center; /* Centraliza horizontalmente */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Sombra sutil */
  transition: all 0.3s ease; /* Transição suave para hover */
}

button[name="radio"]:hover {
  background-color: #8b0909; /* Cor azul escuro no hover */
  box-shadow: 0 6px 10px rgba(0, 0, 0, 0.15); /* Aumenta a sombra */
  transform: translateY(-2px); /* Efeito de leve elevação */
}

button[name="radio"] a {
  text-decoration: none; /* Remove sublinhado do link */
  color: #fff; /* Garante que o texto do link seja branco */
  font-weight: bold; /* Texto em negrito */
}

button[name="radio"]:hover a {
  color: #e0e0e0; /* Texto mais claro no hover */
}

button[name="radio"] span {
  margin-left: 5px; /* Espaço entre ícones e texto */
}

</style>