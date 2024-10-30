<template>
  <!-- Template inicial de uma publicação exibindo o titulo, o conteudo e os comentarios  -->
  <div>
    <h1>{{ publicacao.titulo }}</h1>
    <p>{{ publicacao.texto }}</p>
    <h3>Comentários</h3>

    <!-- Loop pra chamar os comentarios -->
    <ul>
      <li v-for="comentario in comentarios" :key="comentario.id">
        {{ comentario.desc }} - <strong>{{ comentario.usuario.nick }}</strong>
      </li>
    </ul>

    <!-- Adicionando comentarios (Rever essa parte ainda) -->
    <h3>Adicionar Comentário</h3>
    <form @submit.prevent="enviarComentario">
      <textarea v-model="novoComentario.desc" required></textarea>
      <button type="submit">Enviar</button>
    </form>
  </div>
</template>

<script>
// Armazenando dados da API
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

  // Chamada da publicação e dos comentarios pelo Id das publicações 
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

  // Metodo para enviar comentario, ele armazena o id da publicação e cria uma desc baseado 
  // no que o usuario digitar no textarea, 
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