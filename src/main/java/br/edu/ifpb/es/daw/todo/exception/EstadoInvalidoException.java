package br.edu.ifpb.es.daw.todo.exception;

import lombok.Getter;

@Getter
public class EstadoInvalidoException extends PokemonException {
    private static final long serialVersionUID = 1L;

    public EstadoInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }

    public EstadoInvalidoException(String message) {
        super(message);
    }
}