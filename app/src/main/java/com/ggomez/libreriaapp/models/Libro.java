package com.ggomez.libreriaapp.models;

import java.io.Serializable;

public class Libro implements Serializable {
    private int id_libro;
    private String titulo;
    private String descripcion;
    private String fecha;
    private int copias;
    private int paginas;
    private Autor autor;
    private Editorial editorial;
    private Estante estante;

    public Libro(int id_libro, String titulo, String descripcion, String fecha, int copias, int paginas, Autor autor, Editorial editorial, Estante estante) {
        this.id_libro = id_libro;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.copias = copias;
        this.paginas = paginas;
        this.autor = autor;
        this.editorial = editorial;
        this.estante = estante;
    }

    public Libro(String titulo, String descripcion, String fecha, int copias, int paginas, Autor autor, Editorial editorial, Estante estante) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.copias = copias;
        this.paginas = paginas;
        this.autor = autor;
        this.editorial = editorial;
        this.estante = estante;
    }

    public Libro() {
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCopias() {
        return copias;
    }

    public void setCopias(int copias) {
        this.copias = copias;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Estante getEstante() {
        return estante;
    }

    public void setEstante(Estante estante) {
        this.estante = estante;
    }
}
