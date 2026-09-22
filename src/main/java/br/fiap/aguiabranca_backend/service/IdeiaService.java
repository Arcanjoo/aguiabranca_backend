package br.fiap.aguiabranca_backend.service;

import br.fiap.aguiabranca_backend.model.Ideia;
import br.fiap.aguiabranca_backend.repository.IdeiaRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class IdeiaService {

    @Autowired
    private IdeiaRepository repository;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public List<Ideia> listarTodas() {
        return repository.findAll();
    }

    public Ideia salvarComAvaliacaoIA(Ideia ideia) {
        ideia.setStatus("PENDENTE");

        try {
            RestTemplate restTemplate = new RestTemplate();
            // URL limpa sem o parâmetro ?key=
            String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.6-flash:generateContent";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + geminiApiKey);

            String prompt = "Aja como um gestor de inovação. Avalie esta ideia de 0 a 100 e dê uma justificativa curta. Formato OBRIGATÓRIO de resposta: 'Nota: [numero] - Justificativa: [texto]'. A ideia é: " + ideia.getTitulo() + " - " + ideia.getDescricao();

            String requestBody = "{ \"contents\": [{ \"parts\": [{\"text\": \"" + prompt + "\"}] }] }";
            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

            String response = restTemplate.postForObject(url, request, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            String textoIA = root.path("candidates").get(0).path("content").path("parts").get(0).path("text").asText();

            ideia.setJustificativaIA(textoIA);

            String notaStr = textoIA.replaceAll("[^0-9]", "").substring(0, Math.min(2, textoIA.replaceAll("[^0-9]", "").length()));
            ideia.setPontuacaoIA(notaStr.isEmpty() ? 50 : Integer.parseInt(notaStr));

        } catch (Exception e) {
            e.printStackTrace();
            ideia.setJustificativaIA("Não foi possível gerar avaliação da IA neste momento.");
            ideia.setPontuacaoIA(0);
        }

        return repository.save(ideia);
    }
}