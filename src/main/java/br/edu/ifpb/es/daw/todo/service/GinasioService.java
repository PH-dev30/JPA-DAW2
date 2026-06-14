package br.edu.ifpb.es.daw.todo.service;

import br.edu.ifpb.es.daw.todo.exception.PokemonException;
import br.edu.ifpb.es.daw.todo.mapper.GinasioMapper;
import br.edu.ifpb.es.daw.todo.model.Ginasio;
import br.edu.ifpb.es.daw.todo.model.Time;
import br.edu.ifpb.es.daw.todo.repository.GinasioRepository;
import br.edu.ifpb.es.daw.todo.repository.TimeRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.GinasioResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.GinasioSalvarRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GinasioService {

    private final GinasioRepository repository;
    private final GinasioMapper ginasioMapper;
    private final TimeRepository timeRepository;

    @Autowired
    public GinasioService(GinasioRepository repository, GinasioMapper ginasioMapper, TimeRepository timeRepository) {
        this.repository = repository;
        this.ginasioMapper = ginasioMapper;
        this.timeRepository = timeRepository;
    }

    @Transactional
    public GinasioResponseDTO criar(GinasioSalvarRequestDTO dto) {
        Time time = timeRepository.findById(dto.timeId())
                .orElseThrow(() -> new PokemonException("Time não encontrado!"));
        Ginasio ginasioNovo = ginasioMapper.from(dto, time);
        Ginasio ginasioCriado = repository.save(ginasioNovo);
        return ginasioMapper.from(ginasioCriado);
    }

    public List<GinasioResponseDTO> recuperarTodos() {
        return repository.findAll()
                .stream()
                .map(ginasioMapper::from)
                .toList();
    }

    private Ginasio ensureExists(Long id) {
        Optional<Ginasio> ginasioOpt = repository.findById(id);
        return ginasioOpt.orElseThrow(() -> new PokemonException(
                String.format("Entidade 'Ginasio' de id '%s' não foi encontrada!", id)));
    }

    public GinasioResponseDTO buscarPor(Long id) {
        Ginasio ginasio = ensureExists(id);
        return ginasioMapper.from(ginasio);
    }

    @Transactional
    public GinasioResponseDTO atualizar(Long id, GinasioSalvarRequestDTO dto) {
        Ginasio ginasioExistente = ensureExists(id);
        ginasioExistente.setLider(dto.lider());
        ginasioExistente.setNome(dto.nome());
        ginasioExistente.setCidade(dto.cidade());
        ginasioExistente.setTipo(dto.tipo());

        Time time = timeRepository.findById(dto.timeId())
                .orElseThrow(() -> new PokemonException("Time não encontrado!"));
        ginasioExistente.setTime(time);

        Ginasio ginasioAtualizado = repository.save(ginasioExistente);
        return ginasioMapper.from(ginasioAtualizado);
    }

    @Transactional
    public void remover(Long id) {
        Optional<Ginasio> ginasioOpt = repository.findById(id);
        ginasioOpt.ifPresent(ginasio -> repository.delete(ginasio));
    }
}