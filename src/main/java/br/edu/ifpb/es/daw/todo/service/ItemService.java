package br.edu.ifpb.es.daw.todo.service;
import br.edu.ifpb.es.daw.todo.exception.PokemonException;
import br.edu.ifpb.es.daw.todo.mapper.ItemMapper;
import br.edu.ifpb.es.daw.todo.model.Item;
import br.edu.ifpb.es.daw.todo.model.Treinador;
import br.edu.ifpb.es.daw.todo.repository.ItemRepository;
import br.edu.ifpb.es.daw.todo.repository.TreinadorRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.ItemResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.ItemSalvarRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository repository;
    private final ItemMapper itemMapper;

    private final TreinadorRepository treinadorRepository;

    @Autowired
    public ItemService(ItemRepository repository, ItemMapper itemMapper, TreinadorRepository treinadorRepository) {
        this.repository = repository;
        this.itemMapper = itemMapper;
        this.treinadorRepository = treinadorRepository;

    }
    @Transactional
    public ItemResponseDTO criar(ItemSalvarRequestDTO dto) {
        Treinador treinador = treinadorRepository.findById(dto.treinadorId())
                .orElseThrow(() -> new PokemonException("Treinador não encontrado!"));
        Item itemNovo = itemMapper.from(dto, treinador);
        Item itemCriado = repository.save(itemNovo);
        return itemMapper.from(itemCriado);
    }

    public List<ItemResponseDTO> recuperarTodos() {
        return repository.findAll().stream().map(itemMapper::from).toList();
    }

    private Item ensureExists(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new PokemonException(String.format("Entidade 'Item' de id '%s' não foi encontrada!", id)));
    }

    public ItemResponseDTO buscarPor(Long id) {
        return itemMapper.from(ensureExists(id));
    }

    @Transactional
    public ItemResponseDTO atualizar(Long id, ItemSalvarRequestDTO dto) {
        Item itemExistente = ensureExists(id);
        itemExistente.setNome(dto.nome());
        itemExistente.setPreco(dto.preco());
        Treinador treinador = treinadorRepository.findById(dto.treinadorId())
                .orElseThrow(() -> new PokemonException("Treinador não encontrado!"));
        itemExistente.setTreinador(treinador);
        return itemMapper.from(repository.save(itemExistente));
    }

    @Transactional
    public void remover(Long id) {
        repository.findById(id).ifPresent(repository::delete);
    }

}