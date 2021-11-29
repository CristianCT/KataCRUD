package com.sofkau.fullstack.katacrud.repositories;

import com.sofkau.fullstack.katacrud.models.TodoModel;
import org.springframework.data.repository.CrudRepository;

// Definición de la interface que va a mantener conexión constante con el modelo, la base de datos y sus cambios
public interface TodoRepository extends CrudRepository<TodoModel, Long> {
}
