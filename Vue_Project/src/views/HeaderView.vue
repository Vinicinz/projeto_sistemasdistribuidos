<!-- Cabeçalho  mostrando o titulo e criando rotas para cada elemento-->
<template>
    <div class="header">
      <div class="titulo" role="header">
        <a href=""><RouterLink to="/">BACKDOOR</RouterLink></a>
        <div class="cabecalho">
          <li><a href=""><RouterLink to="/artigo">Artigos</RouterLink></a></li>
          <li><a href=""><RouterLink to="/forum">Fórum</RouterLink></a></li>
          <li><a href=""><RouterLink to="/sobreNos">Sobre Nós</RouterLink></a></li>

        </div>
      </div>
    </div>

    <div v-if="isAuthenticated" class="cadastro2" role="navigation">
      <RouterLink to="/user" >
        <profile />
        <div>
          <span class="nickname"> {{ usuario ? usuario.login : 'Usuário' }} </span>
        </div>
      </RouterLink>
    </div>

    <div v-else class="cadastro" role="navigation">
      <button class="sign-in"><a href="#">
          <RouterLink to="/login">Entrar</RouterLink>
        </a></button>
      <button class="sign-out"><a href="#">
          <RouterLink to="/cadastro">Cadastrar</RouterLink>
        </a></button>
    </div>

</template>


<script>
import profile from '@/components/icons/profile.vue';
import { isAuthenticated } from '@/router/auth';
import loginServices from '../../services/login.services';

export default {
  components: {
    profile
  },
  computed: {
    isAuthenticated() {
      return isAuthenticated();
    }
  },
  data() {
    return {
      login: '',
      userId: localStorage.getItem('userId') || '',
      usuario: null
    };
  },
  async mounted() {
    this.pegarNome();  // Corrigido: chamando o método com 'this'
  },
  methods: {

    async pegarNome() {
      const userId = this.userId;  // Obtém o userId do data

      if (!userId) {
        console.error('Usuário não está logado ou o ID não está no localStorage');
        return;
      }

      try {
        const response = await loginServices.buscaLogin(userId);
        this.usuario = response.data; // Agora o 'usuario' é atribuído com os dados da API
        this.login = usuario.login;  // Armazenando o login do usuário no estado do componente

      } catch (error) {
        console.error('Erro ao buscar nome do usuário:', error);
      }
    }
  }
};
</script>