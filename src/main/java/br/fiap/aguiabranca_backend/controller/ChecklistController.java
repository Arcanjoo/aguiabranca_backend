package br.fiap.aguiabranca_backend.controller;

import br.fiap.aguiabranca_backend.dto.ChecklistDTO;
import br.fiap.aguiabranca_backend.model.Checklist;
import br.fiap.aguiabranca_backend.model.Usuario;
import br.fiap.aguiabranca_backend.service.ChecklistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checklists")
@RequiredArgsConstructor
@Tag(name = "Checklists", description = "Inspeções pré-viagem realizadas por operadores")
public class ChecklistController {

    private final ChecklistService checklistService;

    @PostMapping
    @Operation(summary = "Registrar inspeção pré-viagem", description = "Valida os itens de segurança, calcula aprovação e atualiza a quilometragem do veículo.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Checklist processado e salvo"),
            @ApiResponse(responseCode = "400", description = "Veículo inexistente ou falha de validação")
    })
    public ResponseEntity<Checklist> registrar(@RequestBody @Valid ChecklistDTO dto,
                                               @AuthenticationPrincipal Usuario usuario) {
        Checklist checklist = checklistService.registrar(dto, usuario.getEmail());
        return ResponseEntity.ok(checklist);
    }

    @GetMapping
    @Operation(summary = "Listar checklists", description = "Recupera o histórico geral de todas as inspeções realizadas.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<Checklist>> listar() {
        return ResponseEntity.ok(checklistService.listarTodos());
    }

    @GetMapping("/veiculo/{placa}")
    @Operation(summary = "Histórico de checklists por veículo", description = "Filtra todas as vistorias associadas a uma determinada placa.")
    @ApiResponse(responseCode = "200", description = "Histórico do veículo retornado")
    public ResponseEntity<List<Checklist>> listarPorVeiculo(@PathVariable String placa) {
        return ResponseEntity.ok(checklistService.listarPorVeiculo(placa));
    }
}