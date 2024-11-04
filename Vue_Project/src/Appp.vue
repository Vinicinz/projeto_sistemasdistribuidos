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

#container {
    
    width: 816px;
    height: 90px;
    margin-left:200px;
    background-color: #4F2869;
    display: flex;
    justify-content: space-between;
}

.titulo {
  font-size: 40px;
  text-align: left;
  margin-left: 20px;
  padding-top: 13px;
  font-family: fantasy;
}

.cadastro {
  padding-top: 35px;
  margin-left: 50px;
}

.wrapper {
    width: 815px;
    border: 1px solid #c965f0;
    height: 45px;
    background-color: black;
    font-family: Arial;
    font-size: 25px;
    color: white;
    background-color: #52275C;
    display: inline-block;
  }


  .wrapper {
   
    padding: 0px;
    margin: 0px;
    list-style: none;
}

.wrapper ul li {
  padding: 2px 32px;
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
  
    color: #ffffff;
    border-bottom: 3px solid rgb(255, 255, 255);
}

</style>
