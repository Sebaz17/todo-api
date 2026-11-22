package com.tello.todo_api.controller;

import com.tello.todo_api.dto.TareaRequest;
import com.tello.todo_api.dto.TareaResponse;
import com.tello.todo_api.model.Tarea;
import com.tello.todo_api.repository.TareaRepository;
import com.tello.todo_api.service.TareaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {
    private final TareaService tareaService;

    public TareaController(TareaRepository tareaRepository, TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    public List<TareaResponse> listar(){
        return tareaService.listar();
    }

    @PostMapping
    public ResponseEntity<TareaResponse> crear(@Valid @RequestBody TareaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tareaService.crear(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaResponse> buscar(@PathVariable Long id){
        return ResponseEntity.ok(tareaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareaResponse> actualizar(@PathVariable Long id, @Valid @RequestBody TareaRequest request) {
        return ResponseEntity.ok(tareaService.actualizar(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tareaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
