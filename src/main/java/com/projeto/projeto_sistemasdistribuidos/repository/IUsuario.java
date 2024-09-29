package com.projeto.projeto_sistemasdistribuidos.repository;

import com.projeto.projeto_sistemasdistribuidos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface IUsuario extends JpaRepository <Usuario, Integer>{
}
