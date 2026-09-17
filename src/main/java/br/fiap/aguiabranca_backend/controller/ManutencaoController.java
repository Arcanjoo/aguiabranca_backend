package br.fiap.aguiabranca_backend.controller;

import br.fiap.aguiabranca_backend.dto.AtualizarStatusManutencaoDTO;
import br.fiap.aguiabranca_backend.dto.CriarManutencaoDTO;
import br.fiap.aguiabranca_backend.model.Manutencao;
import br.fiap.aguiabranca_backend.model.Usuario;
import br.fiap.aguiabranca_backend.service.ManutencaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manutencoes")
@RequiredArgsConstructor
@Tag(name = "Manutenções", description = "Abertura e acompanhamento de ordens de serviço mecânico")
public class ManutencaoController {

    private final ManutencaoService manutencaoService;

    @PostMapping
    @Operation(summary = "Abrir ordem de manutenção", description = "Registra um chamado mecânico. Vincula automaticamente o operador logado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ordem criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Campos obrigatórios inválidos")
    })
    public ResponseEntity<Manutencao> criar(@RequestBody @Valid CriarManutencaoDTO dto,
                                            @AuthenticationPrincipal Usuario usuario) {
        Manutencao novaManutencao = manutencaoService.criar(dto, usuario.getEmail());
        return ResponseEntity.ok(novaManutencao);
    }

    @GetMapping
    @Operation(summary = "Listar manutenções", description = "Recupera todas as ordens de serviço registradas.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public ResponseEntity<List<Manutencao>> listar() {
        return ResponseEntity.ok(manutencaoService.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar manutenção por ID", description = "Retorna os detalhes de uma ordem de serviço.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ordem localizada"),
            @ApiResponse(responseCode = "400", description = "ID inexistente")
    })
    public ResponseEntity<Manutencao> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(manutencaoService.buscarPorId(id));
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('GESTOR', 'LIDER')")
    @Operation(summary = "Atualizar status da manutenção", description = "Atualiza o andamento ou conclui a ordem. Exige perfil GESTOR ou LIDER.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Permissão insuficiente"),
            @ApiResponse(responseCode = "400", description = "Ordem não encontrada ou dados inválidos")
    })
    public ResponseEntity<Manutencao> atualizarStatus(@PathVariable String id,
                                                      @RequestBody @Valid AtualizarStatusManutencaoDTO dto) {
        return ResponseEntity.ok(manutencaoService.atualizarStatus(id, dto));
    }
}