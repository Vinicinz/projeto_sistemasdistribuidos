<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<!-- Tela pricipal com um menu de navegação e duas rotas -->
<template>
  <header>
    <div id="container">
      <div class="titulo" role="header">
        <a href=""><RouterLink to="/">BACKDOOR</RouterLink></a>
        <div class="cabecalho">
        <li><a href=""><RouterLink to="/artigos">Artigos</RouterLink></a></li>
        <li><a href=""><RouterLink to="/forum">Fórum</RouterLink></a></li>
        <li><a href=""><RouterLink to="/sobreNos">Sobre Nós</RouterLink></a></li>
      </div>
      </div> 

      <div class="cadastro" role="navigation"> 
      <button class="sign-in"><a href="#"><RouterLink to="/login">Sign in</RouterLink></a></button>
      <button class="sign-out"><a href="#"><RouterLink to="/login">Sign Out</RouterLink></a></button>
      </div>

    </div>
  </header>

  <!-- RouterView que será chamado dependendo da URL -->
  <RouterView />
</template>

<!-- Script para iniciar o template do HomeView -->
<script>
import HomeView from './views/HomeView.vue';
export default {
  components: {
    HomeView
  }
};
</script>
