package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.PokemonResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.PokemonSalvarRequestDTO;
import br.edu.ifpb.es.daw.todo.service.PokemonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class PokemonRestController implements PokemonRestControllerApi {

    private final PokemonService pokemonService;

    @Autowired
    public PokemonRestController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<PokemonResponseDTO>> listar() {
        List<PokemonResponseDTO> resultado = pokemonService.recuperarTodos();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<PokemonResponseDTO> adicionar(@RequestBody @Valid PokemonSalvarRequestDTO dto) {
        PokemonResponseDTO resultado = pokemonService.criar(dto);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PokemonResponseDTO> recuperarPor(@PathVariable Long id) {
        PokemonResponseDTO resultado = pokemonService.buscarPor(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<PokemonResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid PokemonSalvarRequestDTO dto) {
        PokemonResponseDTO resultado = pokemonService.atualizar(id, dto);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        pokemonService.remover(id);
        return ResponseEntity.noContent().build();
    }

}
