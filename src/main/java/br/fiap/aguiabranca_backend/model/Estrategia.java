package br.fiap.aguiabranca_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "estrategias")
public class Estrategia {

    @Id
    private String id;
    private LocalDate data = LocalDate.now();
    private String categoria;
    private String campanha;

    // Gere os Getters e Setters (No IntelliJ: Alt + Insert -> Getter and Setter)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getCampanha() { return campanha; }
    public void setCampanha(String campanha) { this.campanha = campanha; }
}