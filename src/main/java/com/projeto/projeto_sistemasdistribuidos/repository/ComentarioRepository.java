package com.projeto.projeto_sistemasdistribuidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projeto.projeto_sistemasdistribuidos.model.Comentario;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

    List<Comentario> findByPublicacaoId(Integer publicacaoId);
}
