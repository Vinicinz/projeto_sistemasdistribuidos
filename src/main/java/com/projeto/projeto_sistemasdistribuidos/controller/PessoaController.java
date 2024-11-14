package com.projeto.projeto_sistemasdistribuidos.controller;

import com.projeto.projeto_sistemasdistribuidos.service.PessoaService;
import com.projeto.projeto_sistemasdistribuidos.model.Pessoa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarPessoas() {
        return ResponseEntity.status(200).body(service.listarPessoa());
    }

    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        return ResponseEntity.status(201).body(service.criarPessoa(pessoa));
    }
    @PutMapping
    public ResponseEntity<Pessoa> editarPessoa(@RequestBody Pessoa pessoa) {
        return ResponseEntity.status(200).body(service.editarPessoa(pessoa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirPessoa(@PathVariable Integer id) {
        service.excluirPessoa(id);
        return ResponseEntity.status(204).build();
    }

}
