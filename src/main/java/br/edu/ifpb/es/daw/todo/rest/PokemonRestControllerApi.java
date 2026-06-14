package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.PokemonResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.PokemonSalvarRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "pokemon")
public interface PokemonRestControllerApi {

    @Operation(summary = "Listar todos os pokémons.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<List<PokemonResponseDTO>> listar();

    @Operation(summary = "Criar um novo pokémon.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Requisição inválida."),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.")
    })
    ResponseEntity<PokemonResponseDTO> adicionar(PokemonSalvarRequestDTO dto);

    @Operation(summary = "Buscar pokémon por id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso."),
            @ApiResponse(responseCode = "400", description = "Pokémon não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.")
    })
    ResponseEntity<PokemonResponseDTO> recuperarPor(
            @Parameter(description = "Id do pokémon.")
            Long id);

    @Operation(summary = "Atualizar pokémon.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso."),
            @ApiResponse(responseCode = "400", description = "Pokémon não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.")
    })
    ResponseEntity<PokemonResponseDTO> atualizar(
            @Parameter(description = "Id do pokémon.")
            Long id,
            PokemonSalvarRequestDTO dto);

    @Operation(summary = "Remover pokémon.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Removido com sucesso."),
            @ApiResponse(responseCode = "400", description = "Pokémon não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.")
    })
    ResponseEntity<Void> remover(
            @Parameter(description = "Id do pokémon.")
            Long id);
}