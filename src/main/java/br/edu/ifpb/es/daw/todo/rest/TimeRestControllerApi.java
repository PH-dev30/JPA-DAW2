package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.TimeResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.TimeSalvarRequestDTO;
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

@Tag(name = "time")
public interface TimeRestControllerApi {

    @Operation(summary = "Listar todos os times.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TimeResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<List<TimeResponseDTO>> listar();

    @Operation(summary = "Criar um novo time.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TimeResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<TimeResponseDTO> adicionar(TimeSalvarRequestDTO dto);

    @Operation(summary = "Buscar time por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TimeResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Time não encontrado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<TimeResponseDTO> recuperarPor(
            @Parameter(description = "Id do time.")
            Long id);

    @Operation(summary = "Atualizar time existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TimeResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Time não encontrado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<TimeResponseDTO> atualizar(
            @Parameter(description = "Id do time.")
            Long id,
            TimeSalvarRequestDTO dto);

    @Operation(summary = "Remover time existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Removido com sucesso.",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Time não encontrado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<Void> remover(
            @Parameter(description = "Id do time.")
            Long id);
}