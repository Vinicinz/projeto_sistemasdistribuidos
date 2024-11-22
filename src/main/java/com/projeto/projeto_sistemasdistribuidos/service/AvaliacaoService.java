package com.projeto.projeto_sistemasdistribuidos.service;

import com.projeto.projeto_sistemasdistribuidos.model.Publicacao;
import com.projeto.projeto_sistemasdistribuidos.model.Usuario;
import org.springframework.stereotype.Service;
import com.projeto.projeto_sistemasdistribuidos.model.Avaliacao;
import com.projeto.projeto_sistemasdistribuidos.repository.AvaliacaoRepository;
import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {

    private AvaliacaoRepository repository;

    public AvaliacaoService(AvaliacaoRepository repository){
        this.repository = repository;
    }

    public List<Avaliacao> ListarAvaliacao(){
        List<Avaliacao> listar = repository.findAll();
        return listar;
    }

    public Integer contarAvaliacoesPorPublicacaoId(Integer publicacaoId){
        return repository.countByPublicacaoId(publicacaoId);

    }

    public void toggleAvaliacao(Avaliacao avaliacao) {
        Optional<Avaliacao> avaliacaoExistente = repository.findByUsuarioAndPublicacao(avaliacao.getUsuario(), avaliacao.getPublicacao());

        if (avaliacaoExistente.isPresent()) {
            repository.delete(avaliacaoExistente.get());
        } else {
            Avaliacao novaAvaliacao = new Avaliacao();
            novaAvaliacao.setUsuario(avaliacao.getUsuario());
            novaAvaliacao.setPublicacao(avaliacao.getPublicacao());
            novaAvaliacao.setReact(1);

            repository.save(novaAvaliacao);
        }
    }

    public Avaliacao editarAvaliacao(Avaliacao avaliacao){
        Avaliacao novaAvaliacao = repository.save(avaliacao);
        return novaAvaliacao;
    }

    public boolean excluirAvaliacao(Integer id){
        repository.deleteById(id);
        return true;
    }
}
