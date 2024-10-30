import axios from 'axios'

class ArtigosService {
  getArtigo ( idArtigo ) {
    return axios.get(`http://localhost:8080/publicacao/${idArtigo}`)
  }
}

export default new ArtigosService()