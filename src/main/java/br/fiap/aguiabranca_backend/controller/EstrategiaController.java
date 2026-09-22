package br.fiap.aguiabranca_backend.controller;

import br.fiap.aguiabranca_backend.model.Estrategia;
import br.fiap.aguiabranca_backend.service.EstrategiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estrategias")
public class EstrategiaController {

    @Autowired
    private EstrategiaService service;

    @GetMapping
    public ResponseEntity<List<Estrategia>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @PostMapping
    public ResponseEntity<Estrategia> criar(@RequestBody Estrategia estrategia) {
        return ResponseEntity.ok(service.salvar(estrategia));
    }
}