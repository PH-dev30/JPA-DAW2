package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.TreinadorResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.TreinadorSalvarRequestDTO;
import br.edu.ifpb.es.daw.todo.service.TreinadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treinador")
public class TreinadorRestController implements TreinadorRestControllerApi{

    private final TreinadorService treinadorService;

    @Autowired
    public TreinadorRestController(TreinadorService treinadorService) {
        this.treinadorService = treinadorService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<TreinadorResponseDTO>> listar() {
        List<TreinadorResponseDTO> resultado = treinadorService.recuperarTodos();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<TreinadorResponseDTO> adicionar(@RequestBody @Valid TreinadorSalvarRequestDTO dto) {
        TreinadorResponseDTO resultado = treinadorService.criar(dto);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TreinadorResponseDTO> recuperarPor(@PathVariable Long id) {
        TreinadorResponseDTO resultado = treinadorService.buscarPor(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<TreinadorResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid TreinadorSalvarRequestDTO dto) {
        TreinadorResponseDTO resultado = treinadorService.atualizar(id, dto);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        treinadorService.remover(id);
        return ResponseEntity.noContent().build();
    }

}