package com.projeto.projeto_sistemasdistribuidos.controller;

import com.projeto.projeto_sistemasdistribuidos.UsuarioService.PermissaoService;
import com.projeto.projeto_sistemasdistribuidos.model.Permissao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissao")
public class PermissaoController {

    private PermissaoService service;

    public PermissaoController(PermissaoService service) {this.service = service;}

    @GetMapping
    public ResponseEntity<List<Permissao>> listarPermissao(){
        return ResponseEntity.status(200).body(service.listarPermissao());
    }

    @PostMapping
    public ResponseEntity<Permissao> criarPermissao(@RequestBody Permissao permissao){
        return ResponseEntity.status(201).body(service.criarPermissao(permissao));
    }

    @PutMapping
    public ResponseEntity<Permissao> editarPermissao(@RequestBody Permissao permissao){
        return ResponseEntity.status(200).body(service.criarPermissao(permissao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirPermissao(@PathVariable Integer id){
        service.excluirPermissao(id);
        return ResponseEntity.status(204).build();
    }


}
