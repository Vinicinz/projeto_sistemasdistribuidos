import api from './api';

class AvaliacaoService {
    getAvaliacao(publicacaoId) {
        return api.get(`/avaliacao/count?publicacaoId=${publicacaoId}`);
    }
    postAvaliacao(avaliacaoTogle) {
        try {
            const response = api.post(`/avaliacao/toggle`, avaliacaoTogle);
            return response.data;
        } catch (error) {
            console.error('Erro ao curtir/descurtir publicacao:', error);
            throw error;
        }
    }
}

// Exporta uma instância da classe
export default new AvaliacaoService();