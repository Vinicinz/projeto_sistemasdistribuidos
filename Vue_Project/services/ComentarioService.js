import axios from "axios";

class ComentarioService {

    getComentarioCount(publicacaoId) {
        return axios.get(`http://localhost:8080/comentario/count?publicacaoId=${publicacaoId}`);
    }
}


export default new ComentarioService();