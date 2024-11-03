<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<!-- Tela pricipal com um menu de navegação e duas rotas -->
<template>
  <header>
    <div id="header">
      <div class="titulo" role="header">
        BACKDOOR
      </div> 
      <div class="cadastro" role="navigation">
        <ul><li><a href="#">Entrar/Cadastrar</a></li></ul>
      </div>
    </div>

    
    <div class="wrapper">
      <nav>
        <ul>
        <li><a href=""><RouterLink to="/">Home</RouterLink></a></li>
        <li><a href=""><RouterLink to="/login">Login</RouterLink></a></li>
        <li><a href=""><RouterLink to="/sobreNos">Sobre Nós</RouterLink></a></li>
        <li><a href=""><RouterLink to="/contato">Contato</RouterLink></a></li>
      
    </ul>
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

<style>

#header {
    
    width: 800px;
    margin-left:200px;
    background-color: rgb(129, 74, 180);
}

.titulo {
  font-size: 35px;
  text-align: left;
  margin-left: 20px;
  padding-top: 15px;
  font-family: fantasy;
}

.cadastro {
  margin-right: 10px;
  text-align: right;
}

.wrapper {

    text-align: center;
    background-color: black;
    font-family: Arial;
    font-size: 25px;
    color: white;
    margin-left:300px;
    background-color: rgb(103, 41, 155);
  }


  .wrapper {
    
    padding: 0px;
    margin: 0px;
    list-style: none;
}

.wrapper ul li {
  padding: 2px 10px;
  display: inline;
    
}

.wrapper ul li a{
  padding: 2px 10px;
  text-decoration: none;
  color: white;
  
}

.wrapper ul li a:hover {
    border-bottom: 3px solid #d2bde0;
}

.cadastro ul li {
  padding: 2px 10px;
  display: inline;
    
}

.cadastro ul li a{
  padding: 2px 10px;
  text-decoration: none;
  color: white;
  
}

.cadastro ul li a:hover {
  
    color: #bd8fe7;
    border-bottom: 3px solid rgb(106, 49, 240);
}

</style>
