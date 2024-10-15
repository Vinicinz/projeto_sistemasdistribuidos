package com.projeto.projeto_sistemasdistribuidos.repository;

import com.projeto.projeto_sistemasdistribuidos.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
