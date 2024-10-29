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

    <h3>Adicionar Comentário</h3>
    <form @submit.prevent="enviarComentario">
      <textarea v-model="novoComentario.desc" required></textarea>
      <button type="submit">Enviar</button>
    </form>


  </div>
</template>

<script>
export default {
  data() {
    return {
      publicacao: {},
      comentarios: [],
      novoComentario: {
        desc: '',
        usuario: {
          id: '5'
        }
      }
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
  },
  methods: {
    enviarComentario() {
      const publicacaoId = this.$route.params.id;

      const comentarioPayload = {
        ...this.novoComentario,
        publicacao: { id: publicacaoId }
      };

      fetch('http://localhost:8080/comentario', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(comentarioPayload)
      })
        .then(response => response.json())
        .then(novoComentario => {
          this.comentarios.push(novoComentario);
          this.novoComentario.desc = '';
        })
        .catch(error => {
          console.error('Erro ao enviar comentário:', error);
        });
    }
  }
};
</script>