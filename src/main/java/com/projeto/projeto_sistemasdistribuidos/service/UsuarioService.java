package com.projeto.projeto_sistemasdistribuidos.service;

import com.projeto.projeto_sistemasdistribuidos.model.Publicacao;
import com.projeto.projeto_sistemasdistribuidos.repository.UsuarioRepository;
import com.projeto.projeto_sistemasdistribuidos.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;

    }
    public List<Usuario> listarUsuario() {
        List<Usuario> lista = repository.findAll();
        return lista;
    }

    public Usuario criarUsuario(Usuario usuario) {
        Usuario usuarioNovo = repository.save(usuario);
        return usuarioNovo;
    }

    public Usuario editarUsuario(Usuario usuario) {
        Usuario usuarioNovo = repository.save(usuario);
        return usuarioNovo;
    }

    public Boolean excluirUsuario(Integer id) {
        repository.deleteById(id);
        return true;
    }

    public Usuario getUsuarioById(Integer id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + email));
    }
}
