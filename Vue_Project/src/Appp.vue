<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<!-- Tela pricipal com um menu de navegação e duas rotas -->
<template>
  <header>
    <div class="wrapper">
      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/login">Login</RouterLink>
        <RouterLink to="/cadastro">Cadastro</RouterLink>

      </nav>
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
