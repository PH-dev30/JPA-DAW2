package br.edu.ifpb.es.daw.todo.service;

import br.edu.ifpb.es.daw.todo.exception.EstadoInvalidoException;
import br.edu.ifpb.es.daw.todo.exception.PokemonException;
import br.edu.ifpb.es.daw.todo.mapper.PokemonMapper;
import br.edu.ifpb.es.daw.todo.model.Movimento;
import br.edu.ifpb.es.daw.todo.model.Pokemon;
import br.edu.ifpb.es.daw.todo.repository.MovimentoRepository;
import br.edu.ifpb.es.daw.todo.repository.PokemonRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.PokemonResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.PokemonSalvarRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PokemonService {

    private final PokemonRepository repository;
    private final PokemonMapper pokemonMapper;
    private final MovimentoRepository movimentoRepository;

    @Autowired
    public PokemonService(PokemonRepository repository,
                          PokemonMapper pokemonMapper,
                          MovimentoRepository movimentoRepository) {
        this.repository = repository;
        this.pokemonMapper = pokemonMapper;
        this.movimentoRepository = movimentoRepository;
    }

    private void validarMovimentos(PokemonSalvarRequestDTO dto) {

        Set<Long> movimentos = new HashSet<>();

        List<Long> ids = List.of(
                dto.movimento1Id(),
                dto.movimento2Id(),
                dto.movimento3Id(),
                dto.movimento4Id()
        );

        for (Long id : ids) {
            if (id == null) continue;

            if (!movimentos.add(id)) {
                throw new EstadoInvalidoException(
                        "Um Pokémon não pode possuir movimentos repetidos.");
            }
        }
    }

    private Movimento findMovimento(Long id) {
        if (id == null) return null;
        return movimentoRepository.findById(id)
                .orElseThrow(() -> new PokemonException(
                        String.format("Movimento de id '%s' não encontrado!", id)));
    }

    @Transactional
    public PokemonResponseDTO criar(PokemonSalvarRequestDTO dto) {

        validarMovimentos(dto);

        Movimento m1 = findMovimento(dto.movimento1Id());
        Movimento m2 = findMovimento(dto.movimento2Id());
        Movimento m3 = findMovimento(dto.movimento3Id());
        Movimento m4 = findMovimento(dto.movimento4Id());

        Pokemon pokemonNovo = pokemonMapper.from(dto, m1, m2, m3, m4);
        Pokemon pokemonCriado = repository.save(pokemonNovo);
        return pokemonMapper.from(pokemonCriado);
    }

    public List<PokemonResponseDTO> recuperarTodos() {
        return repository.findAll()
                .stream()
                .map(pokemonMapper::from)
                .toList();
    }

    private Pokemon ensureExists(Long id) {
        Optional<Pokemon> pokemonOpt = repository.findById(id);
        return pokemonOpt.orElseThrow(() -> new PokemonException(
                String.format("Entidade 'Pokemon' de id '%s' não foi encontrada!", id)));
    }

    public PokemonResponseDTO buscarPor(Long id) {
        Pokemon pokemon = ensureExists(id);
        return pokemonMapper.from(pokemon);
    }

    @Transactional
    public PokemonResponseDTO atualizar(Long id, PokemonSalvarRequestDTO dto) {
        Pokemon pokemonExistente = ensureExists(id);
        pokemonExistente.setNome(dto.nome());
        pokemonExistente.setTipo1(dto.tipo1());
        pokemonExistente.setTipo2(dto.tipo2());
        pokemonExistente.setRaridade(dto.raridade());
        pokemonExistente.setRegioes(dto.regioes());
        pokemonExistente.setHabilidade(dto.habilidade());
        pokemonExistente.setNatureza(dto.natureza());
        pokemonExistente.setMovimento1(findMovimento(dto.movimento1Id()));
        pokemonExistente.setMovimento2(findMovimento(dto.movimento2Id()));
        pokemonExistente.setMovimento3(findMovimento(dto.movimento3Id()));
        pokemonExistente.setMovimento4(findMovimento(dto.movimento4Id()));

        Pokemon pokemonAtualizado = repository.save(pokemonExistente);
        return pokemonMapper.from(pokemonAtualizado);
    }

    @Transactional
    public void remover(Long id) {
        Optional<Pokemon> pokemonOpt = repository.findById(id);
        pokemonOpt.ifPresent(pokemon -> repository.delete(pokemon));
    }
}
