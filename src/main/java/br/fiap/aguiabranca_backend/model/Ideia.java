package br.fiap.aguiabranca_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "ideias")
public class Ideia {

    @Id
    private String id;
    private String titulo;
    private String descricao;
    private String autorId;
    private String status;
    private Integer pontuacaoIA;
    private String justificativaIA;
    private LocalDateTime dataCriacao = LocalDateTime.now();

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getAutorId() { return autorId; }
    public void setAutorId(String autorId) { this.autorId = autorId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getPontuacaoIA() { return pontuacaoIA; }
    public void setPontuacaoIA(Integer pontuacaoIA) { this.pontuacaoIA = pontuacaoIA; }

    public String getJustificativaIA() { return justificativaIA; }
    public void setJustificativaIA(String justificativaIA) { this.justificativaIA = justificativaIA; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
}