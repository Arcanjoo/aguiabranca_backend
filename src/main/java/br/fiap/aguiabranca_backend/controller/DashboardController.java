package br.fiap.aguiabranca_backend.controller;

import br.fiap.aguiabranca_backend.model.Projeto;
import br.fiap.aguiabranca_backend.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private ProjetoService projetoService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> obterResumo() {
        List<Projeto> projetos = projetoService.listarTodos();

        double roiTotal = projetos.stream()
                .filter(p -> p.getRoi() != null)
                .mapToDouble(Projeto::getRoi)
                .sum();

        double investimentoTotal = projetos.stream()
                .filter(p -> p.getInvestimento() != null)
                .mapToDouble(Projeto::getInvestimento)
                .sum();

        Map<String, Object> resumo = new HashMap<>();
        resumo.put("totalProjetos", projetos.size());
        resumo.put("investimentoGlobal", investimentoTotal);
        resumo.put("roiGlobal", roiTotal);

        return ResponseEntity.ok(resumo);
    }
}