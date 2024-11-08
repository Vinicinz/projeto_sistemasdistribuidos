<!-- Cabeçalho  mostrando o titulo e criando rotas para cada elemento-->
<template>
    <div class="header">
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
</template>