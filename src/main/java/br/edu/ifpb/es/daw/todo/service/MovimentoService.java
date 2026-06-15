package br.edu.ifpb.es.daw.todo.service;

import br.edu.ifpb.es.daw.todo.exception.EstadoInvalidoException;
import br.edu.ifpb.es.daw.todo.exception.PokemonException;
import br.edu.ifpb.es.daw.todo.mapper.MovimentoMapper;
import br.edu.ifpb.es.daw.todo.model.Movimento;
import br.edu.ifpb.es.daw.todo.repository.MovimentoRepository;
import br.edu.ifpb.es.daw.todo.rest.dto.MovimentoBuscarDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.MovimentoResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.MovimentoSalvarRequestDTO;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimentoService {

    private final MovimentoRepository repository;
    private final MovimentoMapper movimentoMapper;

    @Autowired
    public MovimentoService(MovimentoMapper movimentoMapper, MovimentoRepository repository) {
        this.repository = repository;
        this.movimentoMapper = movimentoMapper;
    }

    private void validarMovimento(Long id, MovimentoSalvarRequestDTO dto) {

        boolean existe;

        if (id == null) {
            existe = repository.existsByNomeDoPoderIgnoreCase(dto.nomeDoPoder());
        } else {
            existe = repository.existsByIdNotAndNomeDoPoderIgnoreCase(
                    id,
                    dto.nomeDoPoder()
            );
        }

        if (existe) {
            throw new EstadoInvalidoException(
                    String.format(
                            "Já existe um movimento com o nome '%s'.",
                            dto.nomeDoPoder()
                    )
            );
        }
    }

    @Transactional
    public MovimentoResponseDTO criar(MovimentoSalvarRequestDTO dto) {

        validarMovimento(null, dto);

        Movimento movNovo = movimentoMapper.from(dto);
        Movimento movCriado = repository.save(movNovo);
        return movimentoMapper.from(movCriado);

    }

    public List<MovimentoResponseDTO> recuperarTodos() {
        return repository.findAll()
                .stream()
                .map(movimentoMapper::from).
                toList();
    }

    private Movimento ensureExists(Long id) {
        Optional<Movimento> movimentoopt = repository.findById(id);
        Movimento mov = movimentoopt.orElseThrow(() -> new PokemonException(String.format("Entidade 'Movimento' de id '%s' não foi encontrada!", id)));
        return mov;
    }

    public MovimentoResponseDTO buscarPor(Long id) {
        Movimento mov = ensureExists(id);
        return movimentoMapper.from(mov);
    }

    @Transactional
    public MovimentoResponseDTO atualizar(Long id, MovimentoSalvarRequestDTO dto) {
        validarMovimento(id, dto);

        Movimento movExistente = ensureExists(id);
        movExistente.setNomeDoPoder(dto.nomeDoPoder());
        movExistente.setPoder(dto.poder());
        movExistente.setTipo(dto.tipo());
        movExistente.setTipoDivisao(dto.tipoDivisao());
        Movimento movAtualizado = repository.save(movExistente);
        return movimentoMapper.from(movAtualizado);
    }

    public Page<MovimentoResponseDTO> buscar(MovimentoBuscarDTO dto) {

        String nome = dto.nomeDoPoder();

        if (nome == null) {
            nome = "";
        }

        Page<Movimento> page = repository.buscarPor(
                nome,
                dto.poderMin(),
                dto.poderMax(),
                dto.tipo(),
                dto.tipoDivisao(),
                PageRequest.of(
                        dto.numeroPagina() == null ? 0 : dto.numeroPagina(),
                        dto.tamanhoPagina() == null ? 10 : dto.tamanhoPagina()
                )
        );

        return page.map(movimentoMapper::from);
    }

    @Transactional
    public void remover(Long id) {
        Optional<Movimento> movimentoOpt = repository.findById(id);
        movimentoOpt.ifPresent(movimento -> repository.delete(movimento));
    }

}
