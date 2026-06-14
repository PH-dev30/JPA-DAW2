package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.BatalhaResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.BatalhaSalvarRequestDTO;
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

@Tag(name = "batalha")
public interface BatalhaRestControllerApi {

    @Operation(summary = "Listar todas as batalhas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BatalhaResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<List<BatalhaResponseDTO>> listar();

    @Operation(summary = "Criar uma nova batalha.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criada com sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BatalhaResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<BatalhaResponseDTO> adicionar(BatalhaSalvarRequestDTO dto);

    @Operation(summary = "Buscar batalha por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BatalhaResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Batalha não encontrada.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<BatalhaResponseDTO> recuperarPor(
            @Parameter(description = "Id da batalha.")
            Long id);

    @Operation(summary = "Atualizar batalha existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BatalhaResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Batalha não encontrada.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<BatalhaResponseDTO> atualizar(
            @Parameter(description = "Id da batalha.")
            Long id,
            BatalhaSalvarRequestDTO dto);

    @Operation(summary = "Remover batalha existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Removida com sucesso.",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Batalha não encontrada.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<Void> remover(
            @Parameter(description = "Id da batalha.")
            Long id);
}