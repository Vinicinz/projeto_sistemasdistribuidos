import api from './api';

class PublicacaoService {
  
  async criarPublicacao(publicacao) {
    try {
      const response = await api.post('/publicacao', publicacao);
      return response.data; // Retorna os dados da publicação criada
    } catch (error) {
      console.error('Erro ao criar publicação:', error);
      throw error;
    }
  }

  getPublicacaoUnica(idPublicacao) {
    return api.get(`publicacao/${idPublicacao}`);
  }

  getPublicacoes() {
    return api.get(`/publicacao`);
  }


  getPublicacaoUsuario(userId){
    return api.get(`/publicacao/usuario/${userId}`, userId)
  }
  
}

export default new PublicacaoService();
