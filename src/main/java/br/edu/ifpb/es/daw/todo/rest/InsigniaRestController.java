package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.InsigniaResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.InsigniaSalvarRequestDTO;
import br.edu.ifpb.es.daw.todo.service.InsigniaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insignia")
public class InsigniaRestController implements InsigniaRestControllerApi {

    private final InsigniaService insigniaService;

    @Autowired
    public InsigniaRestController(InsigniaService insigniaService) {
        this.insigniaService = insigniaService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<InsigniaResponseDTO>> listar() {
        List<InsigniaResponseDTO> resultado = insigniaService.recuperarTodos();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<InsigniaResponseDTO> adicionar(@RequestBody @Valid InsigniaSalvarRequestDTO dto) {
        InsigniaResponseDTO resultado = insigniaService.criar(dto);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<InsigniaResponseDTO> recuperarPor(@PathVariable Long id) {
        InsigniaResponseDTO resultado = insigniaService.buscarPor(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<InsigniaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid InsigniaSalvarRequestDTO dto) {
        InsigniaResponseDTO resultado = insigniaService.atualizar(id, dto);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        insigniaService.remover(id);
        return ResponseEntity.noContent().build();
    }

}
