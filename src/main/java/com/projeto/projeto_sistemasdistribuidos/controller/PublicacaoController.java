package com.projeto.projeto_sistemasdistribuidos.controller;

import com.projeto.projeto_sistemasdistribuidos.UsuarioService.PublicacaoService;
import com.projeto.projeto_sistemasdistribuidos.model.Publicacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publicacao")
public class PublicacaoController {

    private PublicacaoService service;

    public PublicacaoController(PublicacaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Publicacao>> listarPublicacao() {
        return ResponseEntity.status(200).body(service.listarPublicacao());
    }

    @PostMapping
    public ResponseEntity<Publicacao> criarPublicacao(@RequestBody Publicacao publicacao) {
        return ResponseEntity.status(201).body(service.criarPublicacao(publicacao));
    }

    @PutMapping
    public ResponseEntity<Publicacao> editarPublicacao(@RequestBody Publicacao publicacao) {
        return ResponseEntity.status(200).body(service.editarPublicacao(publicacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirPublicacao(@PathVariable Integer id) {
        service.excluirPublicacao(id);
        return ResponseEntity.status(204).build();
    }

}
