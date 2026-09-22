package br.fiap.aguiabranca_backend.service;

import br.fiap.aguiabranca_backend.model.Projeto;
import br.fiap.aguiabranca_backend.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository repository;

    public List<Projeto> listarTodos() {
        return repository.findAll();
    }

    public Projeto salvar(Projeto projeto) {
        return repository.save(projeto);
    }
}