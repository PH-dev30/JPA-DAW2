package br.edu.ifpb.es.daw.todo.service;

import br.edu.ifpb.es.daw.todo.exception.PokemonException;
import br.edu.ifpb.es.daw.todo.mapper.TreinadorMapper;
import br.edu.ifpb.es.daw.todo.model.Treinador;
import br.edu.ifpb.es.daw.todo.repository.TreinadorRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.TreinadorResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.TreinadorSalvarRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreinadorService {

    private final TreinadorRepository repository;
    private final TreinadorMapper treinadorMapper;

    @Autowired
    public TreinadorService(TreinadorMapper treinadorMapper, TreinadorRepository repository) {
        this.repository = repository;
        this.treinadorMapper = treinadorMapper;
    }

    @Transactional
    public TreinadorResponseDTO criar(TreinadorSalvarRequestDTO dto) {
        Treinador treinadorNovo = treinadorMapper.from(dto);
        Treinador treinadorCriado = repository.save(treinadorNovo);
        return treinadorMapper.from(treinadorCriado);

    }

    public List<TreinadorResponseDTO> recuperarTodos() {
        return repository.findAll()
                .stream()
                .map(treinadorMapper::from).
                toList();
    }

    private Treinador ensureExists(Long id) {
        Optional<Treinador> treinadorOpt = repository.findById(id);
        Treinador trainer = treinadorOpt.orElseThrow(() -> new PokemonException(String.format("Entidade 'Treinador' de id '%s' não foi encontrada!", id)));
        return trainer;
    }

    public TreinadorResponseDTO buscarPor(Long id) {
        Treinador trainer = ensureExists(id);
        return treinadorMapper.from(trainer);
    }

    @Transactional
    public TreinadorResponseDTO atualizar(Long id, TreinadorSalvarRequestDTO dto) {
        Treinador treinadorExistente = ensureExists(id);
        treinadorExistente.setNome(dto.nome());
        Treinador treinadorAtualizado = repository.save(treinadorExistente);
        return treinadorMapper.from(treinadorAtualizado);
    }

    @Transactional
    public void remover(Long id) {
        Optional<Treinador> treinadorOpt = repository.findById(id);
        treinadorOpt.ifPresent(treinador -> repository.delete(treinador));
    }

}