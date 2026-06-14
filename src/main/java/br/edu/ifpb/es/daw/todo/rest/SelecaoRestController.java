package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.SelecaoResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.SelecaoSalvarRequestDTO;
import br.edu.ifpb.es.daw.todo.service.SelecaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/selecao")
public class SelecaoRestController implements SelecaoRestControllerApi {

    private final SelecaoService selecaoService;

    @Autowired
    public SelecaoRestController(SelecaoService selecaoService) {
        this.selecaoService = selecaoService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<SelecaoResponseDTO>> listar() {
        List<SelecaoResponseDTO> resultado = selecaoService.recuperarTodos();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<SelecaoResponseDTO> adicionar(@RequestBody @Valid SelecaoSalvarRequestDTO dto) {
        SelecaoResponseDTO resultado = selecaoService.criar(dto);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{timeId}/{pokemonId}")
    public ResponseEntity<SelecaoResponseDTO> recuperarPor(@PathVariable Long timeId, @PathVariable Long pokemonId) {
        SelecaoResponseDTO resultado = selecaoService.buscarPor(timeId, pokemonId);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{timeId}/{pokemonId}")
    public ResponseEntity<SelecaoResponseDTO> atualizar(@PathVariable Long timeId, @PathVariable Long pokemonId, @RequestBody @Valid SelecaoSalvarRequestDTO dto) {
        SelecaoResponseDTO resultado = selecaoService.atualizar(timeId, pokemonId, dto);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{timeId}/{pokemonId}")
    public ResponseEntity<Void> remover(@PathVariable Long timeId, @PathVariable Long pokemonId) {
        selecaoService.remover(timeId, pokemonId);
        return ResponseEntity.noContent().build();
    }
    
}
