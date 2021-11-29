package com.sofkau.fullstack.katacrud.services;

import com.sofkau.fullstack.katacrud.models.TodoModel;
import com.sofkau.fullstack.katacrud.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Se define el servicio que va a utilizar los metodos del repositorio
@Service
public class TodoService {

    // Se inyecta el repositorio con los metodos del CRUD
    @Autowired
    private TodoRepository repository;

    // Obtener todos los registros de la tabla
    public Iterable<TodoModel> list(){
        return repository.findAll();
    }

    // Guardar un registro y obtener el registro guardado ya con su respectivo ID
    public TodoModel save (TodoModel todoModle){
        return repository.save(todoModle);
    }

    // Eliminar un registro de la tabla
    public void delete(Long id){
        repository.delete(get(id));
    }

    // Obtener un determinado registro filtrado por su ID
    public TodoModel get(Long id){
        return repository.findById(id).orElseThrow();
    }
}
