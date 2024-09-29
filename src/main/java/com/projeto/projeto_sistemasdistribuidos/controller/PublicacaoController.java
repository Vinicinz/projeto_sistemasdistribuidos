package com.projeto.projeto_sistemasdistribuidos.controller;

import com.projeto.projeto_sistemasdistribuidos.UsuarioService.PublicacaoService;
import com.projeto.projeto_sistemasdistribuidos.model.Publicacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
