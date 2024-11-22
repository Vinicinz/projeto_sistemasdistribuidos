import axios from 'axios'

class AvaliacaoService {
    getAvaliacao(publicacaoId) {
        return axios.get(`http://localhost:8080/avaliacao/count?publicacaoId=${publicacaoId}`);
    }
    postAvaliacao(avaliacaoTogle) {
        try {
            const response = axios.post(`http://localhost:8080/avaliacao/toggle`, avaliacaoTogle);
            return response.data;
        } catch (error) {
            console.error('Erro ao curtir/descurtir publicacao:', error);
            throw error;
        }
    }
}

// Exporta uma instância da classe
export default new AvaliacaoService();