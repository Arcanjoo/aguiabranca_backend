package br.fiap.aguiabranca_backend.repository;

import br.fiap.aguiabranca_backend.model.Projeto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjetoRepository extends MongoRepository<Projeto, String> {
}