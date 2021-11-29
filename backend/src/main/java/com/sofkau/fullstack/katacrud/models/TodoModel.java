package com.sofkau.fullstack.katacrud.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// Se define el modelo que van a tener los datos al guardarse en la base de datos
@Entity
public class TodoModel {

    @Id // Se indica que es un ID, por lo tanto es una columna que va a tener un valor unico para cada registro
    @GeneratedValue // Indica que se va a generar un valor autoincremental
    private Long id;
    private String name;
    private boolean isCompleted;

    public Long getId() {
        return id;
    }

    // Se definen los metodos GET y SET de cada atributo

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
