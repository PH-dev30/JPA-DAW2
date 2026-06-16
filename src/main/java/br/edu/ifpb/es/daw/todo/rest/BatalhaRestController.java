package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.BatalhaResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.BatalhaSalvarRequestDTO;
import br.edu.ifpb.es.daw.todo.service.BatalhaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batalha")
public class BatalhaRestController implements BatalhaRestControllerApi{

    private final BatalhaService batalhaService;

    @Autowired
    public BatalhaRestController(BatalhaService batalhaService) {
        this.batalhaService = batalhaService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<BatalhaResponseDTO>> listar() {
        List<BatalhaResponseDTO> resultado = batalhaService.recuperarTodos();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<BatalhaResponseDTO> adicionar(@RequestBody @Valid BatalhaSalvarRequestDTO dto) {
        BatalhaResponseDTO resultado = batalhaService.criar(dto);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<BatalhaResponseDTO> recuperarPor(@PathVariable Long id) {
        BatalhaResponseDTO resultado = batalhaService.buscarPor(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<BatalhaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid BatalhaSalvarRequestDTO dto) {
        BatalhaResponseDTO resultado = batalhaService.atualizar(id, dto);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        batalhaService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/simular")
    public ResponseEntity<BatalhaResponseDTO> simular(
            @PathVariable Long id) {

        BatalhaResponseDTO resultado =
                batalhaService.simular(id);

        return ResponseEntity.ok(resultado);
    }

}
