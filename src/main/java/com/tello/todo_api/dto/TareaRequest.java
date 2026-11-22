package com.tello.todo_api.dto;

import com.tello.todo_api.model.Tarea;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TareaRequest(
        @NotBlank(message = "El título es obligatorio")
        @Size(max = 100, message = "El título no puede pasar de 100 caracteres")
        String titulo,

        @Size(max= 255, message = "La descripción no puede pasar de 255 caracteres")
        String descripcion,
        Boolean completada
) {
    public Tarea toEntity() {
        Tarea tarea = new Tarea();
        tarea.setTitulo(titulo);
        tarea.setDescripcion(descripcion);
        tarea.setCompletada(Boolean.TRUE.equals(completada));
        return tarea;
    }
}
