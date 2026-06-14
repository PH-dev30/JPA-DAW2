package br.edu.ifpb.es.daw.todo.rest.advice;

import br.edu.ifpb.es.daw.todo.exception.EstadoInvalidoException;
import br.edu.ifpb.es.daw.todo.exception.PokemonException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    enum ErrorType {
        ERRO_INESPERADO, REQUISICAO_INVALIDA, ESTADO_INVALIDO, ERRO_DE_VALIDACAO;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception ex) {
        return buildProblemDetail(ex, HttpStatus.INTERNAL_SERVER_ERROR, ErrorType.ERRO_INESPERADO);
    }

    @ExceptionHandler(PokemonException.class)
    public ProblemDetail handlePokemonException(PokemonException ex) {
        return buildProblemDetail(ex, HttpStatus.BAD_REQUEST, ErrorType.REQUISICAO_INVALIDA);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildProblemDetail(ex, HttpStatus.BAD_REQUEST, ErrorType.REQUISICAO_INVALIDA);
    }

    @ExceptionHandler(EstadoInvalidoException.class)
    public ProblemDetail handleEstadoInvalidoException(EstadoInvalidoException ex) {
        return buildProblemDetail(ex, HttpStatus.BAD_REQUEST, ErrorType.ESTADO_INVALIDO);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return buildProblemDetail(ex, HttpStatus.BAD_REQUEST, ErrorType.REQUISICAO_INVALIDA);
    }

    private ProblemDetail buildProblemDetail(Exception ex, HttpStatus status, ErrorType type) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(status, ex.getLocalizedMessage());
        pd.setType(URI.create(type.name()));
        pd.setProperty("trace", stackTraceToString(ex));
        pd.setProperty("timestamp", LocalDateTime.now());
        return pd;
    }

    private ProblemDetail handleValidation(MethodArgumentNotValidException ex) {

        var errors = new java.util.HashMap<String, String>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pd.setType(URI.create(ErrorType.ERRO_DE_VALIDACAO.name()));

        pd.setProperty("erros", errors);
        pd.setProperty("timestamp", LocalDateTime.now());

        return pd;
    }

    @Override
    protected org.springframework.http.ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        return ResponseEntity.badRequest().body(handleValidation(ex));
    }

    private String stackTraceToString(Exception ex) {
        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }
}