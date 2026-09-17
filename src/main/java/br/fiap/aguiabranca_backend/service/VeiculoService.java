package br.fiap.aguiabranca_backend.service;

import br.fiap.aguiabranca_backend.dto.VeiculoDTO;
import br.fiap.aguiabranca_backend.model.Veiculo;
import br.fiap.aguiabranca_backend.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public Veiculo cadastrar(VeiculoDTO dto) {
        String placaFormatada = dto.getPlaca().toUpperCase().trim();

        if (veiculoRepository.existsByPlaca(placaFormatada)) {
            throw new RuntimeException("Veículo com placa " + placaFormatada + " já está cadastrado.");
        }

        Veiculo veiculo = Veiculo.builder()
                .placa(placaFormatada)
                .prefixo(dto.getPrefixo().trim())
                .modelo(dto.getModelo().trim())
                .ano(dto.getAno())
                .capacidadePassageiros(dto.getCapacidadePassageiros())
                .quilometragem(dto.getQuilometragem())
                .status(dto.getStatus())
                .build();

        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public Veiculo buscarPorPlaca(String placa) {
        return veiculoRepository.findByPlaca(placa.toUpperCase().trim())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado com a placa: " + placa));
    }

    public void excluirPorPlaca(String placa) {
        Veiculo veiculo = buscarPorPlaca(placa); // Garante que lança exceção se a placa não existir
        veiculoRepository.delete(veiculo);       // ou veiculoRepository.deleteByPlaca(placa);
    }
}