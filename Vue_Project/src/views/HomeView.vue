<template>
  <div class="header-buttons">
    <button class="new-btn">Novo</button>
    <button class="top-btn">Top</button>
    <button class="trending-btn">Do Momento</button>
  </div>
  <div class="post-container">
    <div class="post" v-for="publicacao in publicacoes" :key="publicacao.id">
      <router-link :to="`/artigo/${publicacao.id}`">
        <div class="post-header">
          <img src="https://via.placeholder.com/40" alt="User Profile">
          <div class="user-info">
            <div>
              <span class="username"> {{ publicacao.usuario.login }} </span> 
              <span> {{ publicacao.verificacao }} </span>
            </div>
            <span class="time"> {{ new Date(publicacao.dataPublicacao).toLocaleDateString() }}</span>
          </div>
        </div>
        <div class="post-content">
          <h4>{{ publicacao.titulo }}</h4>
          <p>{{ publicacao.texto }}</p>
        </div>
        <div class="post-footer">
          <span>Gostei | Comentar </span>
          <span> 0 </span>
        </div>
      </router-link>
    </div>
  </div>
</template>


<script>
// Armazenando as Publicações
export default {
  data() {
    return {
      publicacoes: [],
    };
  },

  // Chamando o EndPoint do Back e armazenando o publicações no data
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