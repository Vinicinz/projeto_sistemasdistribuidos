<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<!-- Tela pricipal com um menu de navegação e duas rotas -->
<template>
  <header>
    <div class="header">
      <div class="titulo" role="header">
        <a href="">
          <RouterLink to="/">BACKDOOR</RouterLink>
        </a>
        <div class="cabecalho">
          <li><a href="">
              <RouterLink to="/artigos">Artigos</RouterLink>
            </a></li>
          <li><a href="">
              <RouterLink to="/forum">Fórum</RouterLink>
            </a></li>
          <li><a href="">
              <RouterLink to="/sobreNos">Sobre Nós</RouterLink>
            </a></li>
        </div>
      </div>

      <div class="cadastro" role="navigation">
        <button class="sign-in"><a href="#">
            <RouterLink to="/login">Sign in</RouterLink>
          </a></button>
        <button class="sign-out"><a href="#">
            <RouterLink to="/login">Sign Out</RouterLink>
          </a></button>
      </div>

    </div>

    <div class="container">
      <div class="sidebar">
        <input type="text " placeholder="pesquise">
        <h3>Menu</h3>
        <ul>
          <li><a href="" class="active">Questões</a></li>
          <li><a href="">Categorias</a></li>
          <li><a href="">Artigos</a></li>
        </ul>
      </div>
      <!-- botoes em cima-->
      <div class="main-content">
        <!-- publiccacçõaes -->
        <RouterView />
      </div>

      <!-- Painel na direitas-->
      <div class="right-panel">
        <h4>Considere Ler</h4>
        <hr>
        <ul class="links">
          <li><a href="#">Link 1</a></li>
          <li><a href="#">Link 2</a></li>
          <li><a href="#">Link 3</a></li>
        </ul>
        <h4>Links Uteis</h4>
        <hr>
        <ul class="links">
          <li><a href="#">Link 4</a></li>
          <li><a href="#">Link 5</a></li>
          <li><a href="#">Link 6</a></li>
        </ul>
      </div>
    </div>
  </header>

  <!-- RouterView que será chamado dependendo da URL -->

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
