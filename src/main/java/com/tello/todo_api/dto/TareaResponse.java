package com.tello.todo_api.dto;

import com.tello.todo_api.model.Tarea;

public record TareaResponse(Long id, String titulo, String descripcion, boolean completada) {
    public static TareaResponse desde(Tarea tarea){
        return new TareaResponse(
                tarea.getId(),
                tarea.getTitulo(),
                tarea.getDescripcion(),
                tarea.isCompletada()
        );
    }
}
