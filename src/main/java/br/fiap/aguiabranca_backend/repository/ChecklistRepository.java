package br.fiap.aguiabranca_backend.repository;

import br.fiap.aguiabranca_backend.model.Checklist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChecklistRepository extends MongoRepository<Checklist, String> {
    List<Checklist> findByPlacaVeiculo(String placaVeiculo);
    List<Checklist> findByOperadorEmail(String operadorEmail);
}