package br.fiap.aguiabranca_backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "manutencoes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Manutencao {

    @Id
    private String id;
    private String placaVeiculo;
    private String descricaoProblema;
    private PrioridadeManutencao prioridade;
    private StatusManutencao status;
    private String usuarioAberturaEmail;
    private String observacoesTecnicas;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataConclusao;
}