<template>
    <div class="greetings">
        <div class="public" v-for="publicacao in publicacoes" :key="publicacao.id">
            {{ publicacao.titulo }} - {{ new Date(publicacao.dataPublicacao).toLocaleDateString() }}
            {{ publicacao.verificacao }}
            <br>
            {{ publicacao.usuario.nick }}
            <br>
            {{ publicacao.texto }}

            <li v-for="comentario in getComentarios(publicacao.id)" :key="comentario.id">
                {{ comentario.desc }} - <strong>{{ comentario.usuario.nick }}</strong>
            </li>
            <br>
        </div>
    </div>
</template>


<script>
export default {
    data() {
        return {
            publicacoes: [],
            comentarios: []
        };
    },
    mounted() {
        fetch('http://localhost:8080/publicacao')
            .then(response => response.json())
            .then(data => {
                this.publicacoes = data;
            })
            .catch(error => console.error('Erro ao buscar dados:', error));

        fetch('http://localhost:8080/comentario')
            .then(response => response.json())
            .then(data => {
                this.comentarios = data;
            })
            .catch(error => console.error('Erro ao buscar comentários:', error));
    },
    methods: {
        getComentarios(publicacaoId) {
            return this.comentarios.filter(comentario => comentario.publicacao.id === publicacaoId);
        }
    }
};
</script>


<style>
.public {
  border: solid black 2px;
  margin: 10px;
  padding: 10px;
}
</style>