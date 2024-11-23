import api from './api';

class ComentarioService {
    getComentarioCount(publicacaoId) {
        return api.get(`/comentario/count?publicacaoId=${publicacaoId}`);
    }

    getComentarios(idPublicacao) {
        return api.get(`/comentario/${idPublicacao}`);
    }

    async enviarComentario(comentarioPayload) {
        try {
            const response = await api.post('/comentario', comentarioPayload);
            return response.data;
        } catch (error) {
            console.error('Erro ao criar comentario:', error);
            throw error;
        }
    }
}

export default new ComentarioService();