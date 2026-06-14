package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.GinasioResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.GinasioSalvarRequestDTO;
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

@Tag(name = "ginasio")
public interface GinasioRestControllerApi {

    @Operation(summary = "Listar todos os ginásios.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GinasioResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<List<GinasioResponseDTO>> listar();

    @Operation(summary = "Criar um novo ginásio.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GinasioResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<GinasioResponseDTO> adicionar(GinasioSalvarRequestDTO dto);

    @Operation(summary = "Buscar ginásio por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GinasioResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Ginásio não encontrado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<GinasioResponseDTO> recuperarPor(
            @Parameter(description = "Id do ginásio.")
            Long id);

    @Operation(summary = "Atualizar ginásio existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GinasioResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Ginásio não encontrado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<GinasioResponseDTO> atualizar(
            @Parameter(description = "Id do ginásio.")
            Long id,
            GinasioSalvarRequestDTO dto);

    @Operation(summary = "Remover ginásio existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Removido com sucesso.",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Ginásio não encontrado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<Void> remover(
            @Parameter(description = "Id do ginásio.")
            Long id);
}