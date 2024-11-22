package com.projeto.projeto_sistemasdistribuidos.controller;

import java.util.List;

import com.projeto.projeto_sistemasdistribuidos.model.Publicacao;
import com.projeto.projeto_sistemasdistribuidos.model.Usuario;
import com.projeto.projeto_sistemasdistribuidos.repository.PublicacaoRepository;
import com.projeto.projeto_sistemasdistribuidos.repository.UsuarioRepository;
import com.projeto.projeto_sistemasdistribuidos.service.PublicacaoService;
import com.projeto.projeto_sistemasdistribuidos.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projeto.projeto_sistemasdistribuidos.service.AvaliacaoService;
import com.projeto.projeto_sistemasdistribuidos.model.Avaliacao;


@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    private AvaliacaoService service;
    private PublicacaoRepository publicacaoRepository;
    private UsuarioRepository usuarioRepository;

    public AvaliacaoController(AvaliacaoService service, PublicacaoRepository publicacaoRepository, UsuarioRepository usuarioRepository) {

        this.service = service;
        this.publicacaoRepository = publicacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public ResponseEntity<List<Avaliacao>>listarAvaliacao(){
        return ResponseEntity.status(200).body(service.ListarAvaliacao());
    }


    @PostMapping("/toggle")
    public ResponseEntity<?> toggleAvaliacao(@RequestBody Avaliacao avaliacao) {
        Usuario usuario = usuarioRepository.findById(avaliacao.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Publicacao publicacao = publicacaoRepository.findById(avaliacao.getPublicacao().getId())
                .orElseThrow(() -> new RuntimeException("Publicação não encontrada"));

        service.toggleAvaliacao(avaliacao);
        return ResponseEntity.ok("Operação realizada com sucesso!");
    }

    @GetMapping("/count")
    public ResponseEntity <Integer> contarAvaliacoesPorPublicacao(@RequestParam Integer publicacaoId) {
        Integer total = service.contarAvaliacoesPorPublicacaoId(publicacaoId);
        return ResponseEntity.ok(total);
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
