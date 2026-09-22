package br.fiap.aguiabranca_backend.service;

import br.fiap.aguiabranca_backend.model.Estrategia;
import br.fiap.aguiabranca_backend.repository.EstrategiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstrategiaService {

    @Autowired
    private EstrategiaRepository repository;

    public List<Estrategia> listarTodas() {
        return repository.findAll();
    }

    public Estrategia salvar(Estrategia estrategia) {
        return repository.save(estrategia);
    }
}