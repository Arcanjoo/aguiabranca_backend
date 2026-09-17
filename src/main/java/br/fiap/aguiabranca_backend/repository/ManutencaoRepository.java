package br.fiap.aguiabranca_backend.repository;

import br.fiap.aguiabranca_backend.model.Manutencao;
import br.fiap.aguiabranca_backend.model.StatusManutencao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManutencaoRepository extends MongoRepository<Manutencao, String> {
    List<Manutencao> findByStatus(StatusManutencao status);
    List<Manutencao> findByPlacaVeiculo(String placaVeiculo);
}