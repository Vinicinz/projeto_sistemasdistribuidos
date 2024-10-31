package com.projeto.projeto_sistemasdistribuidos.repository;

import com.projeto.projeto_sistemasdistribuidos.model.Comentario;
import com.projeto.projeto_sistemasdistribuidos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository <Usuario, Integer>{

    Optional<Usuario> getByEmail(String email);
}
