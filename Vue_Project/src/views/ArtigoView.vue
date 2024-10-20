<template>
    <div>
      <h1>{{ publicacao.titulo }}</h1>
      <p>{{ publicacao.texto }}</p>
      <h3>Comentários</h3>
      <ul>
        <li v-for="comentario in comentarios" :key="comentario.id">
          {{ comentario.desc }} - <strong>{{ comentario.usuario.nick }}</strong>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        publicacao: [],
        comentarios: []
      };
    },
    mounted() {
      const publicacaoId = this.$route.params.id;
      fetch(`http://localhost:8080/publicacao/${publicacaoId}`)
        .then(response => response.json())
        .then(data => {
          this.publicacao = data;
        });
  
      fetch(`http://localhost:8080/comentario/${publicacaoId}`)
        .then(response => response.json())
        .then(data => {
          this.comentarios = data;
        });
    }
  };
  </script>