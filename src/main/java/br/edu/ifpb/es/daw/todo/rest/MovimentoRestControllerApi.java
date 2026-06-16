package br.edu.ifpb.es.daw.todo.rest;

import br.edu.ifpb.es.daw.todo.rest.dto.MovimentoBuscarDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.MovimentoResponseDTO;
import br.edu.ifpb.es.daw.todo.rest.dto.MovimentoSalvarRequestDTO;
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

@Tag(name = "movimento")
public interface MovimentoRestControllerApi {

    @Operation(summary = "Listar todos os movimentos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MovimentoResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<List<MovimentoResponseDTO>> listar();

    @Operation(summary = "Criar um novo movimento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MovimentoResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<MovimentoResponseDTO> adicionar(MovimentoSalvarRequestDTO dto);

    @Operation(summary = "Buscar movimento por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MovimentoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Movimento não encontrado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<MovimentoResponseDTO> recuperarPor(@Parameter(description = "Id do movimento.") Long id);

    @Operation(summary = "Atualizar movimento existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MovimentoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Movimento não encontrado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<MovimentoResponseDTO> atualizar(@Parameter(description = "Id do movimento.") Long id,
                                                   MovimentoSalvarRequestDTO dto);

    @Operation(summary = "Remover movimento existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Removido com sucesso.",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Movimento não encontrado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<Void> remover(@Parameter(description = "Id do movimento.") Long id);

    @Operation(
            summary = "Buscar movimentos com filtros e paginação.",
            description = """
                Permite buscar movimentos utilizando filtros opcionais:
                - nomeDoPoder
                - poderMin
                - poderMax
                - tipo
                - tipoDivisao
                
                Os resultados são retornados de forma paginada através dos
                parâmetros numeroPagina e tamanhoPagina.
                """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Erro inesperado.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    ResponseEntity<?> buscar(MovimentoBuscarDTO dto);


}