package com.sofkau.fullstack.katacrud.controllers;

import com.sofkau.fullstack.katacrud.models.TodoModel;
import com.sofkau.fullstack.katacrud.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Se define el controlador de peticiones que usará los servicios disponibles
@RestController
public class TodoController {

    // Se inyectan los servicios al controlador
    @Autowired
    private TodoService service;

    // EndPoint para listar todos los registros
    @GetMapping("api/todos/list")
    public Iterable<TodoModel> list(){
        return service.list();
    }

    // EndPoint para guardar un nuevo registro
    @PostMapping("api/todo")
    public TodoModel update(@RequestBody TodoModel todoModel){
        if(todoModel != null){ // Se valida que se esté recibiendo un nuevo registro
            return service.save(todoModel);
        }
        throw new RuntimeException("No exixte el ID para actualizar");
    }

    // EndPoint para actualizar un registro ya existente
    @PutMapping("api/todo")
    public TodoModel save(@RequestBody TodoModel todoModel){
        return service.save(todoModel);
    }

    // EndPoint para eliminar un registro existente
    @DeleteMapping("api/{id}/todo")
    public void delete(@PathVariable("id") Long id){
        service.delete(id);
    }

    // EndPoint para obtener un registro en especifico
    @GetMapping("api/{id}/todo")
    public TodoModel get(@PathVariable("id") Long id){
        return service.get(id);
    }
}

