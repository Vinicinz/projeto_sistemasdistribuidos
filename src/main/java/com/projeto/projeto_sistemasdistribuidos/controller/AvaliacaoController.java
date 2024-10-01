package com.projeto.projeto_sistemasdistribuidos.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projeto.projeto_sistemasdistribuidos.UsuarioService.AvaliacaoService;
import com.projeto.projeto_sistemasdistribuidos.model.Avaliacao;


@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    private AvaliacaoService service;

    public AvaliacaoController(AvaliacaoService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Avaliacao>>listarAvaliacao(){
        return ResponseEntity.status(200).body(service.ListarAvaliacao());
    }

    @PostMapping
    public ResponseEntity<Avaliacao> criarAvaliacao(@RequestBody Avaliacao avaliacao){
        return ResponseEntity.status(201).body(service.criarAvaliacao(avaliacao));
    }

    @PutMapping
    public ResponseEntity<Avaliacao> editarAvaliacao(@RequestBody Avaliacao avaliacao){
        return ResponseEntity.status(200).body(service.editarAvaliacao(avaliacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirAvaliacao(@PathVariable Integer id){
        service.excluirAvaliacao(id);
        return ResponseEntity.status(204).build();
    }
}
