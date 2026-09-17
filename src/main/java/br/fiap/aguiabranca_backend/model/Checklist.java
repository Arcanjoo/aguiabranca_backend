package br.fiap.aguiabranca_backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "checklists")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Checklist {

    @Id
    private String id;
    private String placaVeiculo;
    private String operadorEmail;

    // Itens inspecionados (true = Ok, false = Irregular)
    private Boolean pneusCalibrados;
    private Boolean nivelOleoAdequado;
    private Boolean freiosOperacionais;
    private Boolean iluminacaoFuncional;
    private Boolean limpadoresFuncionais;
    private Boolean extintorValido;

    private Long quilometragemAtual;
    private ResultadoChecklist resultado;
    private String observacoes;
    private LocalDateTime dataInspecao;
}