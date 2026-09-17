package br.fiap.aguiabranca_backend.dto;

import br.fiap.aguiabranca_backend.model.PrioridadeManutencao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CriarManutencaoDTO {

    @NotBlank(message = "A placa do veículo é obrigatória")
    private String placaVeiculo;

    @NotBlank(message = "A descrição do problema é obrigatória")
    private String descricaoProblema;

    @NotNull(message = "A prioridade é obrigatória")
    private PrioridadeManutencao prioridade;
}