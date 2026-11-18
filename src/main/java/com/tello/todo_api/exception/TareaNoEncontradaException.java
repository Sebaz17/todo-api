package com.tello.todo_api.exception;

public class TareaNoEncontradaException extends RuntimeException {
    public TareaNoEncontradaException(long id) {
        super("No existe la tarea con id " + id);
    }
}
