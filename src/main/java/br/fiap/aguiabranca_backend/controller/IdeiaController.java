package br.fiap.aguiabranca_backend.controller;

import br.fiap.aguiabranca_backend.model.Ideia;
import br.fiap.aguiabranca_backend.service.IdeiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ideias")
public class IdeiaController {

    @Autowired
    private IdeiaService service;

    @GetMapping
    public ResponseEntity<List<Ideia>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @PostMapping
    public ResponseEntity<Ideia> criar(@RequestBody Ideia ideia) {
        // Envia a ideia para ser avaliada pelo Google Gemini antes de guardar
        return ResponseEntity.ok(service.salvarComAvaliacaoIA(ideia));
    }
}