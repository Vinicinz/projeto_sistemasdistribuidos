package com.projeto.projeto_sistemasdistribuidos.repository;

import com.projeto.projeto_sistemasdistribuidos.model.Publicacao;
import com.projeto.projeto_sistemasdistribuidos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import com.projeto.projeto_sistemasdistribuidos.model.Avaliacao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {

    Optional<Avaliacao> findByUsuarioAndPublicacao(Usuario usuario, Publicacao publicacao);

    @Query("SELECT COUNT(p) FROM Avaliacao p WHERE p.publicacao.id = :publicacaoId")
    Integer countByPublicacaoId(@Param("publicacaoId") Integer publicacaoId);



}
