package com.projeto.projeto_sistemasdistribuidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projeto.projeto_sistemasdistribuidos.model.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
}
