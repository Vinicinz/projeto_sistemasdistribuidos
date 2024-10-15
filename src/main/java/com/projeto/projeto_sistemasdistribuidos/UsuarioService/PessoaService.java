package com.projeto.projeto_sistemasdistribuidos.UsuarioService;

import com.projeto.projeto_sistemasdistribuidos.model.Pessoa;
import com.projeto.projeto_sistemasdistribuidos.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public List<Pessoa> listarPessoa() {
        List<Pessoa> lista = repository.findAll();
        return lista;
    }
    public Pessoa criarPessoa(Pessoa pessoa) {
        Pessoa novaPessoa = repository.save(pessoa);
        return novaPessoa;
    }
    public Pessoa editarPessoa (Pessoa pessoa) {
        Pessoa novaPessoa = repository.save(pessoa);
        return novaPessoa;
    }

    public boolean excluirPessoa (Integer id) {
        repository.deleteById(id);
        return true;
    }

}
