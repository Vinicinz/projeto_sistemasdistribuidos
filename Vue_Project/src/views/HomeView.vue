<template>
  <div class="greetings">
      <div class="public" v-for="publicacao in publicacoes" :key="publicacao.id">~
        <router-link :to="`/artigo/${publicacao.id}`">
          {{ publicacao.titulo }}
          {{ publicacao.verificacao }}
          <br>
          {{ publicacao.usuario.nick }}
          <br>
          {{ publicacao.texto }}
          <br>
          {{ new Date(publicacao.dataPublicacao).toLocaleDateString() }}
        </router-link>

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
  font-size: 20px;


}
.public a {
  text-decoration: none;
  color: inherit; 
}
</style>
