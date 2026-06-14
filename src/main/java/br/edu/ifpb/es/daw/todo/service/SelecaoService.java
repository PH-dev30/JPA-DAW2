package br.edu.ifpb.es.daw.todo.service;

import br.edu.ifpb.es.daw.todo.exception.PokemonException;
import br.edu.ifpb.es.daw.todo.mapper.SelecaoMapper;
import br.edu.ifpb.es.daw.todo.model.Pokemon;
import br.edu.ifpb.es.daw.todo.model.Selecao;
import br.edu.ifpb.es.daw.todo.model.SelecaoId;
import br.edu.ifpb.es.daw.todo.model.Time;
import br.edu.ifpb.es.daw.todo.repository.PokemonRepository;
import br.edu.ifpb.es.daw.todo.repository.SelecaoRepository;
import br.edu.ifpb.es.daw.todo.repository.TimeRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.SelecaoResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.SelecaoSalvarRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelecaoService {
    private final SelecaoRepository repository;
    private final SelecaoMapper selecaoMapper;
    private final TimeRepository timeRepository;
    private final PokemonRepository pokemonRepository;

    @Autowired
    public SelecaoService(SelecaoRepository repository, SelecaoMapper selecaoMapper,
                          TimeRepository timeRepository, PokemonRepository pokemonRepository) {
        this.repository = repository;
        this.selecaoMapper = selecaoMapper;
        this.timeRepository = timeRepository;
        this.pokemonRepository = pokemonRepository;
    }

    private Time findTime(Long id) {
        return timeRepository.findById(id)
                .orElseThrow(() -> new PokemonException("Time não encontrado!"));
    }

    private Pokemon findPokemon(Long id) {
        return pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonException("Pokemon não encontrado!"));
    }

    private Selecao ensureExists(Long timeId, Long pokemonId) {
        SelecaoId id = new SelecaoId();
        id.setTimeId(timeId);
        id.setPokemonId(pokemonId);
        return repository.findById(id).orElseThrow(() ->
                new PokemonException("Seleção não encontrada!"));
    }

    @Transactional
    public SelecaoResponseDTO criar(SelecaoSalvarRequestDTO dto) {
        Time time = findTime(dto.timeId());
        Pokemon pokemon = findPokemon(dto.pokemonId());
        Selecao selecaoNova = selecaoMapper.from(dto, time, pokemon);
        Selecao selecaoCriada = repository.save(selecaoNova);
        return selecaoMapper.from(selecaoCriada);
    }

    public List<SelecaoResponseDTO> recuperarTodos() {
        return repository.findAll().stream().map(selecaoMapper::from).toList();
    }

    public SelecaoResponseDTO buscarPor(Long timeId, Long pokemonId) {
        return selecaoMapper.from(ensureExists(timeId, pokemonId));
    }

    @Transactional
    public SelecaoResponseDTO atualizar(Long timeId, Long pokemonId, SelecaoSalvarRequestDTO dto) {
        Selecao selecaoExistente = ensureExists(timeId, pokemonId);
        selecaoExistente.setPosicao(dto.posicao());
        return selecaoMapper.from(repository.save(selecaoExistente));
    }

    @Transactional
    public void remover(Long timeId, Long pokemonId) {
        SelecaoId id = new SelecaoId();
        id.setTimeId(timeId);
        id.setPokemonId(pokemonId);
        repository.findById(id).ifPresent(repository::delete);
    }

}