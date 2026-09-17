package br.fiap.aguiabranca_backend.dto;

import br.fiap.aguiabranca_backend.model.StatusManutencao;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AtualizarStatusManutencaoDTO {

    @NotNull(message = "O novo status é obrigatório")
    private StatusManutencao status;

    private String observacoesTecnicas;
}