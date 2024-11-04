<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<!-- Tela pricipal com um menu de navegação e duas rotas -->
<template>
  <header>
    <div id="container">
      <div class="titulo" role="header">
        BACKDOOR
      </div> 
      <div class="cadastro" role="navigation">
        <ul><li><a href="#"><RouterLink to="/login">Entrar</RouterLink></a></li>/
        <li><a href="#"><RouterLink to="/login">Cadastrar</RouterLink></a></li></ul>
      </div>
    </div>

    
    <div class="wrapper">
      <nav>
        <ul>
        <li><a href=""><RouterLink to="/">Home</RouterLink></a></li>
        <li><a href=""><RouterLink to="/categoria">Categoria</RouterLink></a></li>
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

#container {  
    width: 100%;
    height: 120px;
    background-color: #4F2869;
    display: flex;
    justify-content: space-between;
}

.titulo {
  font-size: 45px;
  text-align: left;
  margin-left: 20px;
  padding-top: 2rem;
  font-family: fantasy;
}

.cadastro {
  font-size: 25px;
  padding-top:  3rem;
  margin-right: 40px;
}

.wrapper {
    width: 100%;
    border: 1px solid #c965f0;
    height: 60px;
    background-color: black;
    font-family: Arial;
    font-size: 25px;
    color: white;
    background-color: #52275C;
  }


  .wrapper {
   
    padding: 5px;
    margin: 0px;
    list-style: none;
}

.wrapper ul li {
  margin-left: 60px;
  margin-top: 5px;
  display: inline-block;
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
  padding: 2px;
  text-decoration: none;
  color: white;
  
}

.cadastro ul li a:hover {
    color: #ffffff;
    border-bottom: 3px solid rgb(255, 255, 255);
}

</style>
