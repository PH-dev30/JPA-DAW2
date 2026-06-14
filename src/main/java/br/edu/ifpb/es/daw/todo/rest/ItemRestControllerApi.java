package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.ItemResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.ItemSalvarRequestDTO;
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

@Tag(name = "item")
public interface ItemRestControllerApi {

    @Operation(summary = "Listar todos os itens.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ItemResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<List<ItemResponseDTO>> listar();

    @Operation(summary = "Criar um novo item.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ItemResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<ItemResponseDTO> adicionar(ItemSalvarRequestDTO dto);

    @Operation(summary = "Buscar item por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ItemResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Item não encontrado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<ItemResponseDTO> recuperarPor(
            @Parameter(description = "Id do item.")
            Long id);

    @Operation(summary = "Atualizar item existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ItemResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Item não encontrado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<ItemResponseDTO> atualizar(
            @Parameter(description = "Id do item.")
            Long id,
            ItemSalvarRequestDTO dto);

    @Operation(summary = "Remover item existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Removido com sucesso.",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Item não encontrado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<Void> remover(
            @Parameter(description = "Id do item.")
            Long id);
}