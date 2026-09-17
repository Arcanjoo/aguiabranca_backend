package br.fiap.aguiabranca_backend.service;

import br.fiap.aguiabranca_backend.dto.ChecklistDTO;
import br.fiap.aguiabranca_backend.model.*;
import br.fiap.aguiabranca_backend.repository.ChecklistRepository;
import br.fiap.aguiabranca_backend.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChecklistService {

    private final ChecklistRepository checklistRepository;
    private final VeiculoRepository veiculoRepository;

    public Checklist registrar(ChecklistDTO dto, String operadorEmail) {
        String placaFormatada = dto.getPlacaVeiculo().toUpperCase().trim();

        Veiculo veiculo = veiculoRepository.findByPlaca(placaFormatada)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado com a placa: " + placaFormatada));

        boolean aprovado = Boolean.TRUE.equals(dto.getPneusCalibrados())
                && Boolean.TRUE.equals(dto.getNivelOleoAdequado())
                && Boolean.TRUE.equals(dto.getFreiosOperacionais())
                && Boolean.TRUE.equals(dto.getIluminacaoFuncional())
                && Boolean.TRUE.equals(dto.getLimpadoresFuncionais())
                && Boolean.TRUE.equals(dto.getExtintorValido());

        ResultadoChecklist resultado = aprovado ? ResultadoChecklist.APROVADO : ResultadoChecklist.REPROVADO;

        // Atualiza a quilometragem e o status operacional do veículo
        veiculo.setQuilometragem(dto.getQuilometragemAtual());
        if (resultado == ResultadoChecklist.REPROVADO) {
            veiculo.setStatus(StatusVeiculo.EM_MANUTENCAO);
        }
        veiculoRepository.save(veiculo);

        Checklist checklist = Checklist.builder()
                .placaVeiculo(placaFormatada)
                .operadorEmail(operadorEmail)
                .pneusCalibrados(dto.getPneusCalibrados())
                .nivelOleoAdequado(dto.getNivelOleoAdequado())
                .freiosOperacionais(dto.getFreiosOperacionais())
                .iluminacaoFuncional(dto.getIluminacaoFuncional())
                .limpadoresFuncionais(dto.getLimpadoresFuncionais())
                .extintorValido(dto.getExtintorValido())
                .quilometragemAtual(dto.getQuilometragemAtual())
                .resultado(resultado)
                .observacoes(dto.getObservacoes())
                .dataInspecao(LocalDateTime.now())
                .build();

        return checklistRepository.save(checklist);
    }

    public List<Checklist> listarTodos() {
        return checklistRepository.findAll();
    }

    public List<Checklist> listarPorVeiculo(String placa) {
        return checklistRepository.findByPlacaVeiculo(placa.toUpperCase().trim());
    }
}