<template>
    <div class="nova-publicacao-container">
        <h2>Criar Nova Publicação</h2>

        <form @submit.prevent="enviarPublicacao">
            <div class="form-group">
                <label for="titulo">Título</label>
                <QuillEditor
                   theme="snow"
                    v-model:content="novaPublicacao.titulo" contentType="html"/>      
                  
            </div>

            <div class="form-group">
                <label for="texto">Texto</label>
                <QuillEditor
                    theme="snow"
                    v-model:content="novaPublicacao.texto" contentType="html"/>            
            </div>

            <div class="form-group">
                <label for="verificacao">Verificado</label>
                <input type="checkbox" id="verificacao" v-model="novaPublicacao.verificacao" />
            </div>

            <div class="form-group">
                <label for="categoriaId">ID da Categoria</label>
                <input type="number" id="categoriaId" v-model.number="novaPublicacao.categoria.id"
                    placeholder="Digite o ID da categoria" required />
            </div>

            <button type="submit">Criar Publicação</button>
        </form>

        <!-- Exibir mensagem de erro -->
        <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
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
                    id: null
                }
            },
            errorMessage: ''
        };
    },

    methods: {
        getDataAtual() {
            const agora = new Date();
            return agora.toISOString();
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

                
                this.novaPublicacao = {
                    texto: '',
                    dataPublicacao: '',
                    verificacao: false,
                    titulo: '',
                    usuario: {
                        id: null,
                        role: '1'
                    },
                    categoria: {
                        id: null
                    }
                };
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
/* Estilo Geral */
.nova-publicacao-container {
  max-width: 600px;
  margin: 40px auto;
  padding: 30px;
  background: linear-gradient(135deg, #f8e0e0, #fff0f0);
  border: none;
  border-radius: 20px;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
  font-family: 'Poppins', sans-serif;
  transition: transform 0.3s ease;
}

.nova-publicacao-container:hover {
  transform: translateY(-5px);
}

/* Título */
.nova-publicacao-container h2 {
  font-size: 2rem;
  font-weight: 700;
  color: #8b0000; /* Vermelho escuro */
  text-align: center;
  margin-bottom: 25px;
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1);
}

/* Estilo do Formulário */
.form-group {
  margin-bottom: 20px;
  position: relative;
}

.form-group label {
  font-size: 0.9rem;
  font-weight: 600;
  color: #8b0000; /* Vermelho escuro */
  margin-bottom: 5px;
  display: block;
}

.form-group input[type="text"],
.form-group input[type="number"] {
  width: 100%;
  padding: 15px;
  border: none;
  border-radius: 15px;
  background: #fde2e2; /* Fundo vermelho claro */
  font-size: 1rem;
  color: #8b0000; /* Vermelho escuro */
  box-shadow: inset 0 3px 6px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
}

.form-group input[type="text"]::placeholder,
.form-group input[type="number"]::placeholder {
  color: #bc6c6c; /* Tom de vermelho mais suave */
}

.form-group input[type="text"]:focus,
.form-group input[type="number"]:focus {
  background: #ffffff;
  box-shadow: 0 0 8px rgba(139, 0, 0, 0.3);
  outline: none;
}

.form-group input[type="checkbox"] {
  width: 20px;
  height: 20px;
  margin-right: 10px;
  accent-color: #8b0000; /* Vermelho escuro */
  border-radius: 4px;
}

/* Quill Editor */
.quill-editor {
  border-radius: 15px;
  border: 1px solid #f5c6c6; /* Cor suave para borda */
  padding: 15px;
  background: #fde2e2; /* Fundo vermelho claro */
  box-shadow: inset 0 3px 6px rgba(0, 0, 0, 0.05);
}

/* Botão */
button {
  width: 100%;
  padding: 15px;
  font-size: 1.1rem;
  font-weight: 600;
  color: #ffffff;
  background: linear-gradient(135deg, #8b0000, #c12a2a); /* Gradiente de vermelho */
  border: none;
  border-radius: 25px;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 5px 15px rgba(139, 0, 0, 0.2);
}

button:hover {
  background: linear-gradient(135deg, #7a0000, #a92a2a); /* Efeito de hover */
  transform: translateY(-3px);
  box-shadow: 0 8px 20px rgba(139, 0, 0, 0.3);
}

button:active {
  transform: translateY(0);
  box-shadow: 0 4px 10px rgba(139, 0, 0, 0.2);
}

/* Mensagem de Erro */
.error-message {
  color: #d32f2f; /* Vermelho vibrante */
  font-size: 0.9rem;
  text-align: center;
  margin-top: 15px;
  animation: fadeIn 0.5s ease;
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

/* Responsividade */
@media (max-width: 768px) {
  .nova-publicacao-container {
    padding: 20px;
    max-width: 90%;
  }

  h2 {
    font-size: 1.8rem;
  }

  button {
    font-size: 1rem;
    padding: 12px;
  }
}
</style>
