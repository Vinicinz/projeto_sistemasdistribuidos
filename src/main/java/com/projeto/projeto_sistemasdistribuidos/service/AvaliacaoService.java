package com.projeto.projeto_sistemasdistribuidos.service;

import org.springframework.stereotype.Service;
import com.projeto.projeto_sistemasdistribuidos.model.Avaliacao;
import com.projeto.projeto_sistemasdistribuidos.repository.AvaliacaoRepository;
import java.util.List;

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

    public Avaliacao criarAvaliacao(Avaliacao avaliacao){
        Avaliacao novaAvaliacao = repository.save(avaliacao);
        return novaAvaliacao;
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
