package com.projeto.projeto_sistemasdistribuidos.controller;

import com.projeto.projeto_sistemasdistribuidos.UsuarioService.ComentarioService;
import com.projeto.projeto_sistemasdistribuidos.model.Comentario;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {

    private ComentarioService service;

    public ComentarioController(ComentarioService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Comentario>> listarComentario(){
        return ResponseEntity.status(200).body(service.ListarComentario());
    }
    
    @PostMapping
    public ResponseEntity<Comentario> criarComentario(@RequestBody Comentario comentario){
        return ResponseEntity.status(201).body(service.criarComentario(comentario));
    }
    
    @PutMapping
    public ResponseEntity<Comentario> editarComentario(@RequestBody Comentario comentario){
        return ResponseEntity.status(200).body(service.editarComentario(comentario));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirComentario(@PathVariable Integer id){
        service.excluirComentario(id);
        return ResponseEntity.status(204).build();
    }
}
