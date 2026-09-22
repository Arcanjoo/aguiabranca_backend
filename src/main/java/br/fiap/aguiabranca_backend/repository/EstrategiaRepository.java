package br.fiap.aguiabranca_backend.repository;

import br.fiap.aguiabranca_backend.model.Estrategia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EstrategiaRepository extends MongoRepository<Estrategia, String> {
}