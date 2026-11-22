package com.tello.todo_api.service;

import com.tello.todo_api.dto.TareaRequest;
import com.tello.todo_api.dto.TareaResponse;
import com.tello.todo_api.exception.TareaNoEncontradaException;
import com.tello.todo_api.model.Tarea;
import com.tello.todo_api.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {
    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    private Tarea obtenerTarea(Long id) {
        return tareaRepository.findById(id)
                .orElseThrow(() -> new TareaNoEncontradaException(id));
    }

    public TareaResponse buscarPorId(Long id) {
        return TareaResponse.desde(obtenerTarea(id));
    }

    public TareaResponse crear(TareaRequest request){
        Tarea guardada = tareaRepository.save(request.toEntity());
        return TareaResponse.desde(guardada);
    }

    public List<TareaResponse> listar(){
        return tareaRepository.findAll().stream()
                .map(TareaResponse::desde).toList();
    }

    public TareaResponse actualizar(Long id, TareaRequest request){
        Tarea tarea = obtenerTarea(id);
        tarea.setTitulo(request.titulo());
        tarea.setDescripcion(request.descripcion());
        tarea.setCompletada(Boolean.TRUE.equals(request.completada()));
        Tarea guardada = tareaRepository.save(tarea);
        return TareaResponse.desde(guardada);
    }

    public void eliminar(Long id){
        Tarea tarea = obtenerTarea(id);
        tareaRepository.delete(tarea);
    }
}
