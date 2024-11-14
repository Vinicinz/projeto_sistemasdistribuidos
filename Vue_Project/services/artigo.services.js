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
    return axios.post('http://localhost:8080/comentario', comentarioPayload, {
      headers: {
        'Content-Type': 'application/json'
      }
    });
  }
}

export default new ArtigosService();