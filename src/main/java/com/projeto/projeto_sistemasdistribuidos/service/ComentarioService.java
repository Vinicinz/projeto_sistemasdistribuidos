package com.projeto.projeto_sistemasdistribuidos.service;

import org.springframework.stereotype.Service;
import com.projeto.projeto_sistemasdistribuidos.model.Comentario;
import com.projeto.projeto_sistemasdistribuidos.repository.ComentarioRepository;
import java.util.List;

@Service
public class ComentarioService {

    private ComentarioRepository repository;

    public ComentarioService(ComentarioRepository repository){
        this.repository = repository;
    }

    public List<Comentario> ListarComentario(){
        List<Comentario> lista = repository.findAll();
        return lista;
    }

    public Integer contarAvaliacoesPorPublicacaoId(Integer publicacaoId){
        return repository.countByPublicacaoId(publicacaoId);

    }
    public Comentario criarComentario(Comentario comentario){
        Comentario novoComentario = repository.save(comentario);
        return novoComentario;
    }

    public Comentario editarComentario(Comentario comentario){
        Comentario novoComentario = repository.save(comentario);
        return novoComentario;
    }

    public boolean excluirComentario(Integer id){
        repository.deleteById(id);
        return true;
    }

    public List<Comentario> buscarComentarioPorId(Integer publicacaoId){
        return repository.findByPublicacaoId(publicacaoId);
    }

}
