
package br.edu.ifpb.es.daw.todo.service;

import br.edu.ifpb.es.daw.todo.exception.PokemonException;
import br.edu.ifpb.es.daw.todo.mapper.BatalhaMapper;
import br.edu.ifpb.es.daw.todo.model.Batalha;
import br.edu.ifpb.es.daw.todo.model.Time;
import br.edu.ifpb.es.daw.todo.model.Treinador;
import br.edu.ifpb.es.daw.todo.repository.BatalhaRepository;
import br.edu.ifpb.es.daw.todo.repository.TimeRepository;
import br.edu.ifpb.es.daw.todo.repository.TreinadorRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.BatalhaResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.BatalhaSalvarRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatalhaService {
    private final BatalhaRepository repository;
    private final BatalhaMapper batalhaMapper;
    private final TreinadorRepository treinadorRepository;
    private final TimeRepository timeRepository;

    @Autowired
    public BatalhaService(BatalhaRepository repository, BatalhaMapper batalhaMapper,
                          TreinadorRepository treinadorRepository, TimeRepository timeRepository) {
        this.repository = repository;
        this.batalhaMapper = batalhaMapper;
        this.treinadorRepository = treinadorRepository;
        this.timeRepository = timeRepository;
    }

    private Treinador findTreinador(Long id) {
        return treinadorRepository.findById(id)
                .orElseThrow(() -> new PokemonException("Treinador não encontrado!"));
    }

    private Time findTime(Long id) {
        if (id == null) return null; // timeVencedor é opcional (empate)
        return timeRepository.findById(id)
                .orElseThrow(() -> new PokemonException("Time não encontrado!"));
    }

    @Transactional
    public BatalhaResponseDTO criar(BatalhaSalvarRequestDTO dto) {
        Treinador t1 = findTreinador(dto.treinador1Id());
        Treinador t2 = findTreinador(dto.treinador2Id());
        Time timeVencedor = findTime(dto.timeVencedorId());
        Batalha batalhaNova = batalhaMapper.from(dto, t1, t2, timeVencedor);
        Batalha batalhaCriada = repository.save(batalhaNova);
        return batalhaMapper.from(batalhaCriada);
    }

    public List<BatalhaResponseDTO> recuperarTodos() {
        return repository.findAll().stream().map(batalhaMapper::from).toList();
    }

    private Batalha ensureExists(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new PokemonException(String.format("Entidade 'Batalha' de id '%s' não foi encontrada!", id)));
    }

    public BatalhaResponseDTO buscarPor(Long id) {
        return batalhaMapper.from(ensureExists(id));
    }

    @Transactional
    public BatalhaResponseDTO atualizar(Long id, BatalhaSalvarRequestDTO dto) {
        Batalha batalhaExistente = ensureExists(id);
        batalhaExistente.setHora(dto.hora());
        batalhaExistente.setCidade(dto.cidade());
        batalhaExistente.setLocal(dto.local());
        batalhaExistente.setResultado(dto.resultado());
        batalhaExistente.setTreinador1(findTreinador(dto.treinador1Id()));
        batalhaExistente.setTreinador2(findTreinador(dto.treinador2Id()));
        batalhaExistente.setTimeVencedor(findTime(dto.timeVencedorId()));
        return batalhaMapper.from(repository.save(batalhaExistente));
    }

    @Transactional
    public void remover(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }
}