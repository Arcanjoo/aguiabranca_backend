package br.fiap.aguiabranca_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChecklistDTO {

    @NotBlank(message = "A placa do veículo é obrigatória")
    private String placaVeiculo;

    @NotNull(message = "A verificação dos pneus é obrigatória")
    private Boolean pneusCalibrados;

    @NotNull(message = "A verificação do óleo é obrigatória")
    private Boolean nivelOleoAdequado;

    @NotNull(message = "A verificação dos freios é obrigatória")
    private Boolean freiosOperacionais;

    @NotNull(message = "A verificação da iluminação é obrigatória")
    private Boolean iluminacaoFuncional;

    @NotNull(message = "A verificação dos limpadores é obrigatória")
    private Boolean limpadoresFuncionais;

    @NotNull(message = "A verificação do extintor é obrigatória")
    private Boolean extintorValido;

    @NotNull(message = "A quilometragem atual é obrigatória")
    @Min(value = 0, message = "A quilometragem não pode ser negativa")
    private Long quilometragemAtual;

    private String observacoes;
}