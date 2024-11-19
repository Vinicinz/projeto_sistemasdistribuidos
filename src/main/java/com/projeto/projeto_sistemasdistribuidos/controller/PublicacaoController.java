package com.projeto.projeto_sistemasdistribuidos.controller;

import com.projeto.projeto_sistemasdistribuidos.service.PublicacaoService;
import com.projeto.projeto_sistemasdistribuidos.model.Publicacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/publicacao")
public class PublicacaoController {

    private PublicacaoService service;

    public PublicacaoController(PublicacaoService service) {
        this.service = service;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping
    public ResponseEntity<List<Publicacao>> listarPublicacao() {
        return ResponseEntity.status(200).body(service.listarPublicacao());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Publicacao> buscarPublicacao(@PathVariable int id) {
        Publicacao publicacao = service.getPublicacaoById(id);
        if (publicacao != null) {
            return ResponseEntity.status(200).body(publicacao);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Publicacao>> buscarPublicacoesPorUsuario(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(service.buscarPublicacoesPorUsuario(usuarioId));
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Publicacao>> buscarPublicacoesPorCategoria(@PathVariable Integer categoriaId ) {
        List<Publicacao> publicacoes = service.listarPorCategoria(categoriaId);
        return ResponseEntity.ok(publicacoes);
    }
    @GetMapping("/novo")
    public ResponseEntity<List<Publicacao>> buscarPublicacoesData() {
        List<Publicacao> publicacoes = service.listarPorData();
        return ResponseEntity.ok(publicacoes);
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
