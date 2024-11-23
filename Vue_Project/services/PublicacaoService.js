import axios from 'axios';

class PublicacaoService {
  
  async criarPublicacao(publicacao) {
    try {
      const response = await axios.post('http://localhost:8080/publicacao', publicacao);
      return response.data; // Retorna os dados da publicação criada
    } catch (error) {
      console.error('Erro ao criar publicação:', error);
      throw error;
    }
  }

  getPublicacaoUnica(idPublicacao) {
    return axios.get(`http://localhost:8080/publicacao/${idPublicacao}`);
  }

  getPublicacoes() {
    return axios.get(`http://localhost:8080/publicacao`);
  }


  getPublicacaoUsuario(userId){
    return axios.get(`http://localhost:8080/publicacao/usuario/${userId}`, userId)
  }
  
}

export default new PublicacaoService();
