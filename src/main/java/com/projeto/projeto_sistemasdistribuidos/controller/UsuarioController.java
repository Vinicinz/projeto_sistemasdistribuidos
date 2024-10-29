package com.projeto.projeto_sistemasdistribuidos.controller;

import com.projeto.projeto_sistemasdistribuidos.UsuarioService.UsuarioService;
import com.projeto.projeto_sistemasdistribuidos.model.Usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("allowedOriginPatterns")
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioservice;

    public UsuarioController(UsuarioService usuariosService) {
        this.usuarioservice = usuariosService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listaUsuarios() {
        return ResponseEntity.status(200).body(usuarioservice.listarUsuario());
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(201).body(usuarioservice.criarUsuario(usuario));
    }

    @PutMapping
    public ResponseEntity<Usuario> editarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(200).body(usuarioservice.editarUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirUsuario(@PathVariable Integer id) {
        usuarioservice.excluirUsuario(id);
        return ResponseEntity.status(204).build();
    }

}
