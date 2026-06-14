
package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.TimeResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.TimeSalvarRequestDTO;
import br.edu.ifpb.es.daw.todo.service.TimeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time")
public class TimeRestController implements TimeRestControllerApi{

    private final TimeService timeService;

    @Autowired
    public TimeRestController(TimeService timeService) {
        this.timeService = timeService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<TimeResponseDTO>> listar() {
        List<TimeResponseDTO> resultado = timeService.recuperarTodos();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<TimeResponseDTO> adicionar(@RequestBody @Valid TimeSalvarRequestDTO dto) {
        TimeResponseDTO resultado = timeService.criar(dto);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TimeResponseDTO> recuperarPor(@PathVariable Long id) {
        TimeResponseDTO resultado = timeService.buscarPor(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<TimeResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid TimeSalvarRequestDTO dto) {
        TimeResponseDTO resultado = timeService.atualizar(id, dto);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        timeService.remover(id);
        return ResponseEntity.noContent().build();
    }
}