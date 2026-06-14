package br.edu.ifpb.es.daw.todo.service;

import br.edu.ifpb.es.daw.todo.exception.PokemonException;
import br.edu.ifpb.es.daw.todo.mapper.InsigniaMapper;
import br.edu.ifpb.es.daw.todo.model.Ginasio;
import br.edu.ifpb.es.daw.todo.model.Insignia;
import br.edu.ifpb.es.daw.todo.model.Treinador;
import br.edu.ifpb.es.daw.todo.repository.GinasioRepository;
import br.edu.ifpb.es.daw.todo.repository.InsigniaRepository;
import br.edu.ifpb.es.daw.todo.repository.TreinadorRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.InsigniaResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.InsigniaSalvarRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsigniaService {
    private final InsigniaRepository repository;
    private final InsigniaMapper insigniaMapper;
    private final TreinadorRepository treinadorRepository;
    private final GinasioRepository ginasioRepository;

    @Autowired
    public InsigniaService(InsigniaRepository repository, InsigniaMapper insigniaMapper,
                           TreinadorRepository treinadorRepository, GinasioRepository ginasioRepository) {
        this.repository = repository;
        this.insigniaMapper = insigniaMapper;
        this.treinadorRepository = treinadorRepository;
        this.ginasioRepository = ginasioRepository;
    }

    @Transactional
    public InsigniaResponseDTO criar(InsigniaSalvarRequestDTO dto) {
        Treinador treinador = treinadorRepository.findById(dto.treinadorId())
                .orElseThrow(() -> new PokemonException("Treinador não encontrado!"));
        Ginasio ginasio = ginasioRepository.findById(dto.ginasioId())
                .orElseThrow(() -> new PokemonException("Ginásio não encontrado!"));
        Insignia insigniaNova = insigniaMapper.from(dto, treinador, ginasio);
        Insignia insigniaCriada = repository.save(insigniaNova);
        return insigniaMapper.from(insigniaCriada);
    }

    public List<InsigniaResponseDTO> recuperarTodos() {
        return repository.findAll().stream().map(insigniaMapper::from).toList();
    }

    private Insignia ensureExists(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new PokemonException(String.format("Entidade 'Insignia' de id '%s' não foi encontrada!", id)));
    }

    public InsigniaResponseDTO buscarPor(Long id) {
        return insigniaMapper.from(ensureExists(id));
    }

    @Transactional
    public InsigniaResponseDTO atualizar(Long id, InsigniaSalvarRequestDTO dto) {
        Insignia insigniaExistente = ensureExists(id);
        insigniaExistente.setNome(dto.nome());
        insigniaExistente.setCidade(dto.cidade());
        insigniaExistente.setDataConquista(dto.dataConquista());
        Treinador treinador = treinadorRepository.findById(dto.treinadorId())
                .orElseThrow(() -> new PokemonException("Treinador não encontrado!"));
        Ginasio ginasio = ginasioRepository.findById(dto.ginasioId())
                .orElseThrow(() -> new PokemonException("Ginásio não encontrado!"));
        insigniaExistente.setTreinador(treinador);
        insigniaExistente.setGinasio(ginasio);
        return insigniaMapper.from(repository.save(insigniaExistente));
    }

    @Transactional
    public void remover(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }
}