<template>
  <div class="greetings">
      <div class="public" v-for="publicacao in publicacoes" :key="publicacao.id">
          {{ publicacao.titulo }} - {{ new Date(publicacao.dataPublicacao).toLocaleDateString() }}
          {{ publicacao.verificacao }}
          <br>
          {{ publicacao.usuario.nick }}
          <br>
          {{ publicacao.texto }}
          <br>
      </div>
  </div>
</template>


<script>
export default {
  data() {
      return {
          publicacoes: [],
      };
  },
  mounted() {
      fetch('http://localhost:8080/publicacao')
          .then(response => response.json())
          .then(data => {
              this.publicacoes = data;
          })
          .catch(error => console.error('Erro ao buscar dados:', error));
  },
};
</script>


<style>
.public {
  border: solid black 2px;
  margin: 10px;
  padding: 10px;
}
</style>
