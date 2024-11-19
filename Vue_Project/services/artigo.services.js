import axios from 'axios';

class ArtigosService {
  getArtigo(idPublicacao) {
    return axios.get(`http://localhost:8080/publicacao/${idPublicacao}`);
  }

  getPublicacoes() {
    return axios.get('http://localhost:8080/publicacao');
  }

  getComentarios(idPublicacao) {
    return axios.get(`http://localhost:8080/comentario/${idPublicacao}`);
  }

  // Enviar um novo comentário
  enviarComentario(comentarioPayload) {
    try {
      const response = axios.post('http://localhost:8080/comentario', comentarioPayload);
      return response.data;
    } catch (error) {
      console.error('Erro ao criar comentario:', error);
      throw error;
    }
  }
}

export default new ArtigosService();