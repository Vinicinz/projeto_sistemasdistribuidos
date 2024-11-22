package com.projeto.projeto_sistemasdistribuidos.repository;

import com.projeto.projeto_sistemasdistribuidos.model.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Integer> {

    @Query("SELECT p FROM Publicacao p WHERE p.categoria.id = :categoriaId ORDER BY p.dataPublicacao DESC")
    List<Publicacao> findByCategoriaId(@Param("categoriaId") Integer categoriaId);


    @Query("SELECT p FROM Publicacao p ORDER BY p.dataPublicacao DESC")
    List<Publicacao> findrByDataPublicacaoDesc();



    List<Publicacao> findByUsuarioId(Integer usuarioId);

    @Override
    Optional<Publicacao> findById(Integer integer);
}
