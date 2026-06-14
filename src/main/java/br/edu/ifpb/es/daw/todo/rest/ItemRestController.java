package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.ItemResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.ItemSalvarRequestDTO;
import br.edu.ifpb.es.daw.todo.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemRestController implements ItemRestControllerApi{

    private final ItemService itemService;

    @Autowired
    public ItemRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> listar() {
        List<ItemResponseDTO> resultado = itemService.recuperarTodos();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ItemResponseDTO> adicionar(@RequestBody @Valid ItemSalvarRequestDTO dto) {
        ItemResponseDTO resultado = itemService.criar(dto);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> recuperarPor(@PathVariable Long id) {
        ItemResponseDTO resultado = itemService.buscarPor(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ItemSalvarRequestDTO dto) {
        ItemResponseDTO resultado = itemService.atualizar(id, dto);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        itemService.remover(id);
        return ResponseEntity.noContent().build();
    }
    
}
