package com.projeto.projeto_sistemasdistribuidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projeto.projeto_sistemasdistribuidos.model.Comentario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

    List<Comentario> findByPublicacaoId(Integer publicacaoId);

    @Query("SELECT COUNT(p) FROM Comentario p WHERE p.publicacao.id = :publicacaoId")
    Integer countByPublicacaoId(@Param("publicacaoId") Integer publicacaoId);


}
