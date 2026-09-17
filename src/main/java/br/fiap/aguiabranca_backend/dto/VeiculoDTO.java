package br.fiap.aguiabranca_backend.dto;

import br.fiap.aguiabranca_backend.model.StatusVeiculo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VeiculoDTO {

    @NotBlank(message = "A placa é obrigatória")
    private String placa;

    @NotBlank(message = "O prefixo do ônibus é obrigatório")
    private String prefixo;

    @NotBlank(message = "O modelo é obrigatório")
    private String modelo;

    @NotNull(message = "O ano é obrigatório")
    private Integer ano;

    @NotNull(message = "A capacidade é obrigatória")
    @Min(value = 1, message = "A capacidade deve ser de pelo menos 1 passageiro")
    private Integer capacidadePassageiros;

    @NotNull(message = "A quilometragem é obrigatória")
    @Min(value = 0, message = "A quilometragem não pode ser negativa")
    private Long quilometragem;

    @NotNull(message = "O status inicial é obrigatório")
    private StatusVeiculo status;
}