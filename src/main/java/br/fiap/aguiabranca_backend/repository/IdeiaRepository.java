package br.fiap.aguiabranca_backend.repository;

import br.fiap.aguiabranca_backend.model.Ideia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IdeiaRepository extends MongoRepository<Ideia, String> {
}