package com.projeto.projeto_sistemasdistribuidos.service;

import com.projeto.projeto_sistemasdistribuidos.model.Publicacao;
import com.projeto.projeto_sistemasdistribuidos.repository.PublicacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicacaoService {

    private PublicacaoRepository repository;

    public PublicacaoService(PublicacaoRepository repository) {
        this.repository = repository;
    }

    public List<Publicacao> listarPublicacao() {
        List<Publicacao> lista = repository.findAll();
        return lista;
    }
    public Publicacao criarPublicacao(Publicacao publicacao) {
        Publicacao novaPublicacao = repository.save(publicacao);
        return novaPublicacao;
    }

    public Publicacao editarPublicacao( Publicacao publicacao){
        Publicacao novaPublicacao = repository.save(publicacao);
        return novaPublicacao;
    }

    public boolean excluirPublicacao(Integer id) {
        repository.deleteById(id);
        return true;
    }
    public Publicacao getPublicacaoById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public List<Publicacao> buscarPublicacoesPorUsuario(Integer usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }
    public List<Publicacao> listarPorCategoria(Integer categoriaId) {
        return repository.findByCategoriaId(categoriaId);
    }
    public List<Publicacao> listarPorData() {
        return repository.findrByDataPublicacaoDesc();
    }


}
