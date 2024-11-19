<template>
  <div class="container-container">
    <div class="nova-publicacao-container">
      <h2>Criar Nova Publicação</h2>

      <!-- Formulário para criar publicação -->
      <form @submit.prevent="enviarPublicacao">
        <div class="form-group">
          <label for="titulo">Título:</label>
          <input type="text" id="titulo" v-model="novaPublicacao.titulo" placeholder="Digite o título da publicação"
            required />
        </div>

        <div class="form-group">
          <label for="texto">Texto:</label>
          <QuillEditor v-model="novaPublicacao.texto" ref="editor" theme="snow" @text-change="onEditorChange" />
        </div>

        <button type="submit">Criar Publicação</button>
      </form>

      <!-- Exibir mensagem de erro -->
      <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
    </div>
  </div>
</template>

<script>
import PublicacaoService from '../../services/PublicacaoService';
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css';

export default {
  components: {
    QuillEditor
  },

  data() {
    return {
      novaPublicacao: {
        texto: '',
        dataPublicacao: '',
        verificacao: false,
        titulo: '',
        usuario: {
          id: null,
          role: '1'
        },
        categoria: {
          id: 1
        }
      },
      errorMessage: ''
    };
  },

  methods: {
    getDataAtual() {
      const agora = new Date();
      return agora.toLocaleString('sv-SE').replace(' ', 'T'); 
    },
    onEditorChange() {
      const editorContent = this.$refs.editor.getText().trim();
      this.novaPublicacao.texto = editorContent || 'falha';
    },
    async enviarPublicacao() {
      try {
        const userId = localStorage.getItem('userId');  

        if (!userId) {
          this.errorMessage = 'Usuário não está logado!';
          return;
        }

        this.novaPublicacao.dataPublicacao = this.getDataAtual();
        this.novaPublicacao.usuario.id = userId; 


        const response = await PublicacaoService.criarPublicacao(this.novaPublicacao);
        console.log('Publicação criada com sucesso:', response);
        alert('Publicação criada com sucesso!');
        
        this.errorMessage = ''; 

      } catch (error) {
        this.errorMessage = 'Erro ao criar a publicação. Verifique os dados.';
        console.error('Erro:', error);
      }
    }
  }
};
</script>


<style scoped>
body {
  font-family: 'Arial', sans-serif;
  background: linear-gradient(to bottom, #f8f9fa, #e9ecef);
  color: #495057;
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
.container-container{
  display: flex;
  justify-content: center;
}
.nova-publicacao-container {
  width: 600px;
  padding: 30px;
  background: #ffffff;
  border: 1px solid #dee2e6;
  border-radius: 15px;
  box-shadow: 0 8px 15px rgba(0, 0, 0, 0.1);
}

.nova-publicacao-container h2 {
  font-size: 1.8rem;
  font-weight: bold;
  margin-bottom: 20px;
  color: #343a40;
  border-bottom: 2px solid #6c757d;
  padding-bottom: 10px;
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  font-size: 1rem;
  font-weight: 600;
  color: #495057;
  margin-bottom: 8px;
}

.form-group input[type="text"],
.form-group input[type="number"],
.form-group input[type="password"],
.form-group input[type="email"],
.form-group textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ced4da;
  border-radius: 8px;
  font-size: 1rem;
  background: #f8f9fa;
  transition: all 0.3s ease;
}

.form-group input[type="text"]:focus,
.form-group input[type="number"]:focus,
.form-group input[type="password"]:focus,
.form-group input[type="email"]:focus,
.form-group textarea:focus {
  border-color: #6c757d;
  outline: none;
  background: #ffffff;
}

.form-group input[type="checkbox"] {
  margin-right: 10px;
}

button {
  width: 100%;
  padding: 12px;
  background-color: #6c757d;
  color: #fff;
  font-size: 1rem;
  font-weight: bold;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

button:hover {
  background-color: #8b0909;
  transform: translateY(-2px);
}

button:active {
  transform: translateY(0);
}

.error-message {
  color: #dc3545;
  font-size: 0.9rem;
  margin-top: 15px;
}

@media (max-width: 768px) {
  .nova-publicacao-container {
    width: 90%;
    padding: 20px;
  }

  .nova-publicacao-container h2 {
    font-size: 1.5rem;
  }

  button {
    font-size: 0.9rem;
  }
}
</style>
