package com.tello.todo_api.service;

import com.tello.todo_api.dto.TareaRequest;
import com.tello.todo_api.dto.TareaResponse;
import com.tello.todo_api.exception.TareaNoEncontradaException;
import com.tello.todo_api.model.Tarea;
import com.tello.todo_api.repository.TareaRepository;
import jakarta.validation.Valid;
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

    public Tarea buscarPorId(long id){
        return  tareaRepository.findById(id).orElseThrow(() -> new TareaNoEncontradaException(id));
    }

    public TareaResponse crear(TareaRequest request){
        Tarea guardada = tareaRepository.save(request.toEntity());
        return TareaResponse.desde(guardada);
    }

    public List<TareaResponse> listar(){
        return tareaRepository.findAll().stream()
                .map(TareaResponse::desde).toList();
    }

    public Tarea actualizar(Long id, TareaRequest request){
        Tarea tarea = obtenerTarea(id);
        tareaRepository.save(request.toEntity());
        return null;
    }

//    public Tarea actualizar(Long id, Tarea datos) {
//        Tarea tarea = buscarPorId(id);
//        tarea.setTitulo(datos.getTitulo());
//        tarea.setDescripcion(datos.getDescripcion());
//        tarea.setCompletada(datos.isCompletada());
//        return tareaRepository.save(tarea);
//    }

    public void eliminar(Long id){
        buscarPorId(id);
        tareaRepository.deleteById(id);
    }
}
