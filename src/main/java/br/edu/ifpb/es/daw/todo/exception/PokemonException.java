package br.edu.ifpb.es.daw.todo.exception;

public class PokemonException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PokemonException(String message, Throwable cause) {
        super(message, cause);
    }

    public PokemonException(String message) {
        super(message);
    }
}