package com.projeto.projeto_sistemasdistribuidos.UsuarioService;

import com.projeto.projeto_sistemasdistribuidos.model.Permissao;
import com.projeto.projeto_sistemasdistribuidos.repository.PermissaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissaoService {

    private PermissaoRepository repository;

    public PermissaoService(PermissaoRepository repository) {this.repository = repository;}

    public List<Permissao> listarPermissao() {
        List<Permissao> lista = repository.findAll();
        return lista;
    }

    public Permissao criarPermissao(Permissao permissao) {
        Permissao novaPermissao = repository.save(permissao);
        return novaPermissao;
    }

    public Permissao editarPermissao(Permissao permissao) {
        Permissao novaPermissao = repository.save(permissao);
        return novaPermissao;
    }

    public boolean excluirPermissao(Integer id) {
        repository.deleteById(id);
        return true;
    }
}
