<template>
    <div class="nova-publicacao-container">
        <h2>Criar Nova Publicação</h2>

        <!-- Formulário para criar publicação -->
        <form @submit.prevent="enviarPublicacao">
            <div class="form-group">
                <label for="titulo">Título:</label>
                <input type="text" id="titulo" v-model="novaPublicacao.titulo"
                    placeholder="Digite o título da publicação" required />
            </div>

            <div class="form-group">
                <label for="texto">Texto:</label>
                <QuillEditor theme="snow" /> 
            </div>

            <div class="form-group">
                <label for="verificacao">Verificado:</label>
                <input type="checkbox" id="verificacao" v-model="novaPublicacao.verificacao" />
            </div>

            <div class="form-group">
                <label for="categoriaId">ID da Categoria:</label>
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
            return agora.toISOString(); // Formato padrão ISO 8601, usado para data e hora
        },
        async enviarPublicacao() {
            try {

                const userId = localStorage.getItem('userId');  // Ou pode ser obtido via decodeJWT, se preferir

                if (!userId) {
                    this.errorMessage = 'Usuário não está logado!';
                    return;
                }

                this.novaPublicacao.dataPublicacao = this.getDataAtual();
                this.novaPublicacao.usuario.id = userId; // Adiciona o userId ao campo 'usuario.id'


                const response = await PublicacaoService.criarPublicacao(this.novaPublicacao);
                console.log('Publicação criada com sucesso:', response);
                alert('Publicação criada com sucesso!');

                // Limpar o formulário após sucesso
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
                this.errorMessage = ''; // Limpar mensagem de erro
            } catch (error) {
                this.errorMessage = 'Erro ao criar a publicação. Verifique os dados.';
                console.error('Erro:', error);
            }
        }
    }
};
</script>

<style scoped>
.nova-publicacao-container {
    max-width: 500px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 8px;
    background-color: #f9f9f9;
}

.form-group {
    margin-bottom: 15px;
}

label {
    display: block;
    margin-bottom: 5px;
}

input[type="text"],
input[type="number"],
input[type="date"],
textarea {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

button {
    padding: 10px 20px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

button:hover {
    background-color: #0056b3;
}

.error-message {
    color: red;
    margin-top: 10px;
}
</style>