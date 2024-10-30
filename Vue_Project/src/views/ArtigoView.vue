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
import ArtigosService from '@/services/artigos.service.js';

export default {
  props: {
    id: {
      type:[Number, String]
    }
  },
  data() {
    return {
      publicacao: [],
      comentarios: []
    };
  },
  mounted() {
    const publicacaoId = this.$route.params.id;
    ArtigosService.getArtigo(publicacaoId).then(
      response => {
        console.log(response.data)
      },
      error => {
        console.error(error.response.data)
      }
    )

    fetch(`http://localhost:8080/comentario/${publicacaoId}`)
      .then(response => response.json())
      .then(data => {
        this.comentarios = data;
      });
    }}

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
        })
      };
    }
</script>