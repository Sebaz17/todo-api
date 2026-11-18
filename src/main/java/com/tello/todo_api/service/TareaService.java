package com.tello.todo_api.service;

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

    public Tarea buscarPorId(long id){
        return  tareaRepository.findById(id).orElseThrow(() -> new TareaNoEncontradaException(id));
    }

    public Tarea crear(@Valid Tarea tarea){
        tarea.setId(null);
        return tareaRepository.save(tarea);
    }

    public List<Tarea> listar(){
        return tareaRepository.findAll();
    }

    public Tarea actualizar(Long id, Tarea datos) {
        Tarea tarea = buscarPorId(id);
        tarea.setTitulo(datos.getTitulo());
        tarea.setDescripcion(datos.getDescripcion());
        tarea.setCompletada(datos.isCompletada());
        return tareaRepository.save(tarea);
    }

    public void eliminar(Long id){
        buscarPorId(id);
        tareaRepository.deleteById(id);
    }
}
