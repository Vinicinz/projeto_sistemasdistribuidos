package com.projeto.projeto_sistemasdistribuidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projeto.projeto_sistemasdistribuidos.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
}
