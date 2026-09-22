package br.fiap.aguiabranca_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "projetos")
public class Projeto {

    @Id
    private String id;
    private String titulo;
    private String etapa;
    private String status;
    private Double investimento;
    private String prazo;
    private Double roi;
    private String estrategiaId; // Para vincular com a Estratégia

    // Gere os Getters e Setters (No IntelliJ: Alt + Insert -> Getter and Setter)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getEtapa() { return etapa; }
    public void setEtapa(String etapa) { this.etapa = etapa; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Double getInvestimento() { return investimento; }
    public void setInvestimento(Double investimento) { this.investimento = investimento; }
    public String getPrazo() { return prazo; }
    public void setPrazo(String prazo) { this.prazo = prazo; }
    public Double getRoi() { return roi; }
    public void setRoi(Double roi) { this.roi = roi; }
    public String getEstrategiaId() { return estrategiaId; }
    public void setEstrategiaId(String estrategiaId) { this.estrategiaId = estrategiaId; }
}