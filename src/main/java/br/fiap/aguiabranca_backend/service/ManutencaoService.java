package br.fiap.aguiabranca_backend.service;

import br.fiap.aguiabranca_backend.dto.AtualizarStatusManutencaoDTO;
import br.fiap.aguiabranca_backend.dto.CriarManutencaoDTO;
import br.fiap.aguiabranca_backend.model.Manutencao;
import br.fiap.aguiabranca_backend.model.StatusManutencao;
import br.fiap.aguiabranca_backend.repository.ManutencaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManutencaoService {

    private final ManutencaoRepository manutencaoRepository;

    public Manutencao criar(CriarManutencaoDTO dto, String usuarioEmail) {
        Manutencao manutencao = Manutencao.builder()
                .placaVeiculo(dto.getPlacaVeiculo().toUpperCase().trim())
                .descricaoProblema(dto.getDescricaoProblema())
                .prioridade(dto.getPrioridade())
                .status(StatusManutencao.ABERTA)
                .usuarioAberturaEmail(usuarioEmail)
                .dataAbertura(LocalDateTime.now())
                .build();

        return manutencaoRepository.save(manutencao);
    }

    public List<Manutencao> listarTodas() {
        return manutencaoRepository.findAll();
    }

    public Manutencao buscarPorId(String id) {
        return manutencaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de manutenção não encontrada: " + id));
    }

    public Manutencao atualizarStatus(String id, AtualizarStatusManutencaoDTO dto) {
        Manutencao manutencao = buscarPorId(id);

        manutencao.setStatus(dto.getStatus());
        if (dto.getObservacoesTecnicas() != null) {
            manutencao.setObservacoesTecnicas(dto.getObservacoesTecnicas());
        }

        if (dto.getStatus() == StatusManutencao.CONCLUIDA) {
            manutencao.setDataConclusao(LocalDateTime.now());
        }

        return manutencaoRepository.save(manutencao);
    }
}