package br.fiap.aguiabranca_backend.controller;

import br.fiap.aguiabranca_backend.dto.VeiculoDTO;
import br.fiap.aguiabranca_backend.model.Veiculo;
import br.fiap.aguiabranca_backend.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
@RequiredArgsConstructor
@Tag(name = "Veículos", description = "Gerenciamento da frota de ônibus da Águia Branca")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @PostMapping
    @PreAuthorize("hasAnyRole('GESTOR', 'LIDER')")
    @Operation(summary = "Cadastrar novo veículo", description = "Adiciona um ônibus à frota. Exige perfil GESTOR ou LIDER.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veículo cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou placa já cadastrada"),
            @ApiResponse(responseCode = "403", description = "Acesso negado para o perfil atual")
    })
    public ResponseEntity<Veiculo> cadastrar(@RequestBody @Valid VeiculoDTO dto) {
        return ResponseEntity.ok(veiculoService.cadastrar(dto));
    }

    @GetMapping
    @Operation(summary = "Listar frota", description = "Retorna todos os veículos registrados no sistema.")
    @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso")
    public ResponseEntity<List<Veiculo>> listar() {
        return ResponseEntity.ok(veiculoService.listarTodos());
    }

    @GetMapping("/{placa}")
    @Operation(summary = "Buscar veículo por placa", description = "Retorna os detalhes de um veículo específico com base na placa.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Veículo localizado"),
            @ApiResponse(responseCode = "400", description = "Veículo não encontrado")
    })
    public ResponseEntity<Veiculo> buscarPorPlaca(@PathVariable String placa) {
        return ResponseEntity.ok(veiculoService.buscarPorPlaca(placa));
    }

    @DeleteMapping("/{placa}")
    @PreAuthorize("hasAnyRole('GESTOR', 'LIDER')")
    @Operation(summary = "Excluir veículo", description = "Remove um ônibus da frota com base na placa. Exige perfil GESTOR ou LIDER.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Veículo excluído com sucesso"),
            @ApiResponse(responseCode = "400", description = "Veículo não encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado para o perfil atual")
    })
    public ResponseEntity<Void> excluir(@PathVariable String placa) {
        veiculoService.excluirPorPlaca(placa);
        return ResponseEntity.noContent().build();
    }
}