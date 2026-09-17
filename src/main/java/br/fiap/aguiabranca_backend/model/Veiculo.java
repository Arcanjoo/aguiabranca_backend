package br.fiap.aguiabranca_backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "veiculos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

    @Id
    private String id;
    private String placa;
    private String prefixo; // Ex: Número operacional do ônibus (ex: 32000)
    private String modelo;  // Ex: Marcopolo Paradiso G8
    private Integer ano;
    private Integer capacidadePassageiros;
    private Long quilometragem;
    private StatusVeiculo status;
}