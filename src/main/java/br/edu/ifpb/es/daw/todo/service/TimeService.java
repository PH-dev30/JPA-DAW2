package br.edu.ifpb.es.daw.todo.service;

import br.edu.ifpb.es.daw.todo.exception.PokemonException;
import br.edu.ifpb.es.daw.todo.mapper.TimeMapper;
import br.edu.ifpb.es.daw.todo.model.Time;
import br.edu.ifpb.es.daw.todo.model.Treinador;
import br.edu.ifpb.es.daw.todo.repository.TimeRepository;
import br.edu.ifpb.es.daw.todo.repository.TreinadorRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.TimeResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.TimeSalvarRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeService {

    private final TimeRepository repository;
    private final TimeMapper timeMapper;

    private final TreinadorRepository treinadorRepository;

    @Autowired
    public TimeService(TimeRepository repository, TimeMapper timeMapper, TreinadorRepository treinadorRepository) {
        this.repository = repository;
        this.timeMapper = timeMapper;
        this.treinadorRepository = treinadorRepository;
    }

    @Transactional
    public TimeResponseDTO criar(TimeSalvarRequestDTO dto) {
        Treinador treinador = treinadorRepository.findById(dto.treinadorId())
                .orElseThrow(() -> new PokemonException("Treinador não encontrado!"));
        Time timeNovo = timeMapper.from(dto, treinador);
        Time timeCriado = repository.save(timeNovo);
        return timeMapper.from(timeCriado);
    }

    public List<TimeResponseDTO> recuperarTodos() {
        return repository.findAll()
                .stream()
                .map(timeMapper::from).
                toList();
    }

    private Time ensureExists(Long id) {
        Optional<Time> timeOpt = repository.findById(id);
        Time time = timeOpt.orElseThrow(() -> new PokemonException(String.format("Entidade 'Time' de id '%s' não foi encontrada!", id)));
        return time;
    }

    public TimeResponseDTO buscarPor(Long id) {
        Time time = ensureExists(id);
        return timeMapper.from(time);
    }

    @Transactional
    public TimeResponseDTO atualizar(Long id, TimeSalvarRequestDTO dto) {
        Time timeExistente = ensureExists(id);
        timeExistente.setNome(dto.nome());
        timeExistente.setTipoTime(dto.tipo());

        Treinador treinador = treinadorRepository.findById(dto.treinadorId()).orElseThrow(() -> new PokemonException("Treinador não encontrado!"));
        timeExistente.setTreinador(treinador);

        Time timeAtualizado = repository.save(timeExistente);
        return timeMapper.from(timeAtualizado);
    }

    @Transactional
    public void remover(Long id) {
        Optional<Time> timeOpt = repository.findById(id);
        timeOpt.ifPresent(time -> repository.delete(time));
    }

}
