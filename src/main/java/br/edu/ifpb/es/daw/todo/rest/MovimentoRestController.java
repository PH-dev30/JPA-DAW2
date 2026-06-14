package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.*;
import br.edu.ifpb.es.daw.todo.service.MovimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimento")
public class MovimentoRestController implements MovimentoRestControllerApi{

    private final MovimentoService movimentoService;

    @Autowired
    public MovimentoRestController(MovimentoService movimentoService) {
        this.movimentoService = movimentoService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<MovimentoResponseDTO>> listar() {
        List<MovimentoResponseDTO> resultado = movimentoService.recuperarTodos();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<MovimentoResponseDTO> adicionar(@RequestBody @Valid MovimentoSalvarRequestDTO dto) {
        MovimentoResponseDTO resultado = movimentoService.criar(dto);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<MovimentoResponseDTO> recuperarPor(@PathVariable Long id) {
        MovimentoResponseDTO resultado = movimentoService.buscarPor(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<MovimentoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid MovimentoSalvarRequestDTO dto) {
        MovimentoResponseDTO resultado = movimentoService.atualizar(id, dto);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        movimentoService.remover(id);
        return ResponseEntity.noContent().build();
    }

}
