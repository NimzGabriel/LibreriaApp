package com.ggomez.libreriaapp.models;

import java.io.Serializable;

public class Editorial implements Serializable {
    private int id_editorial;
    private String nombre;
    private String nacionalidad;

    public Editorial(int id_editorial, String nombre, String nacionalidad) {
        this.id_editorial = id_editorial;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public Editorial(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public Editorial() {
    }

    public int getId_editorial() {
        return id_editorial;
    }

    public void setId_editorial(int id_editorial) {
        this.id_editorial = id_editorial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }
}
