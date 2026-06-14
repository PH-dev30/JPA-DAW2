
package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.GinasioResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.GinasioSalvarRequestDTO;
import br.edu.ifpb.es.daw.todo.service.GinasioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ginasio")
public class GinasioRestController implements GinasioRestControllerApi {

    private final GinasioService ginasioService;

    @Autowired
    public GinasioRestController(GinasioService ginasioService) {
        this.ginasioService = ginasioService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<GinasioResponseDTO>> listar() {
        List<GinasioResponseDTO> resultado = ginasioService.recuperarTodos();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<GinasioResponseDTO> adicionar(@RequestBody @Valid GinasioSalvarRequestDTO dto) {
        GinasioResponseDTO resultado = ginasioService.criar(dto);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<GinasioResponseDTO> recuperarPor(@PathVariable Long id) {
        GinasioResponseDTO resultado = ginasioService.buscarPor(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<GinasioResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid GinasioSalvarRequestDTO dto) {
        GinasioResponseDTO resultado = ginasioService.atualizar(id, dto);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        ginasioService.remover(id);
        return ResponseEntity.noContent().build();
    }
}