import axios from 'axios'

class AvaliacaoService {
    getAvaliacao(publicacaoId) {
        return axios.get(`http://localhost:8080/avaliacao/count?publicacaoId=${publicacaoId}`);
    }
}

// Exporta uma instância da classe
export default new AvaliacaoService();