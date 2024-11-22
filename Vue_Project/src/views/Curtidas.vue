<template>
    <div class="avaliacoes">
      <button 
        @click="CurtireDescurtir" 
        :class="{ 'curtido': curtido }"
        class="botao-curtir"
        :disabled="carregando"
      >
        {{ curtido ? 'Descurtir' : 'Curtir' }}
      </button>
      <span>{{ curtidas }} {{ curtidas === 1 ? 'curtida' : 'curtidas' }}</span>
    </div>
  </template>
  
  <script>

  export default {
    name: "BotaoCurtir",
    props: {
      idPublicacao: {
        type: Number,
        required: true,
      },
      curtidasIniciais: {
        type: Number,
        required: true,
      },
      usuarioCurtiu: {
        type: Boolean,
        required: true,
      },
    },
    data() {
      return {
        curtido: this.usuarioCurtiu, // Estado inicial de curtido
        curtidas: this.curtidasIniciais, // Número inicial de curtidas
        carregando: false, // Indica se está processando
      };
    },
    methods: {
      async alternarCurtir() {
        if (this.carregando) return;
        this.carregando = true;
        try {
          const endpoint = await 
          await axios.post(endpoint);
          this.curtidas += this.curtido ? -1 : 1;
          this.curtido = !this.curtido;
        } catch (erro) {
          console.error("Erro ao curtir/descurtir:", erro);
          alert("Houve um problema ao interagir com a publicação.");
        } finally {
          this.carregando = false;
        }
      },
    },
  };
  </script>
  
  