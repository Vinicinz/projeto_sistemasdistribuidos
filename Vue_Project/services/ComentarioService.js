import axios from "axios";

class ComentarioService {

    getComentarioCount(publicacaoId) {
        return axios.get(`http://localhost:8080/comentario/count?publicacaoId=${publicacaoId}`);
    }

    getComentarios(idPublicacao) {
        return axios.get(`http://localhost:8080/comentario/${idPublicacao}`);
    }

    enviarComentario(comentarioPayload) {
        try {
            const response = axios.post(`http://localhost:8080/comentario`, comentarioPayload);
            return response.data;
        } catch (error) {
            console.error('Erro ao criar comentario:', error);
            throw error;
        }
    }
}


export default new ComentarioService();