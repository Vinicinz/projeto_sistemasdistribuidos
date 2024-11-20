package com.projeto.projeto_sistemasdistribuidos.repository;

import com.projeto.projeto_sistemasdistribuidos.model.Comentario;
import com.projeto.projeto_sistemasdistribuidos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository <Usuario, Integer>{

    Optional<Usuario> findByEmail(String email);

    @Override
    Optional<Usuario> findById(Integer integer);
}
