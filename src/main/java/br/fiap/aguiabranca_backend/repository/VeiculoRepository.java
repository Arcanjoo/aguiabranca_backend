package br.fiap.aguiabranca_backend.repository;

import br.fiap.aguiabranca_backend.model.StatusVeiculo;
import br.fiap.aguiabranca_backend.model.Veiculo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends MongoRepository<Veiculo, String> {
    Optional<Veiculo> findByPlaca(String placa);
    Optional<Veiculo> findByPrefixo(String prefixo);
    boolean existsByPlaca(String placa);
    List<Veiculo> findByStatus(StatusVeiculo status);
}