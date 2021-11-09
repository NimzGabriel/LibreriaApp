package com.ggomez.libreriaapp.models;

import java.io.Serializable;

public class Estante implements Serializable {
    private int id_estante;
    private String letra;
    private int numero;
    private String color;

    public Estante(int id_estante, String letra, int numero, String color) {
        this.id_estante = id_estante;
        this.letra = letra;
        this.numero = numero;
        this.color = color;
    }

    public Estante(String letra, int numero, String color) {
        this.letra = letra;
        this.numero = numero;
        this.color = color;
    }

    public Estante() {
    }

    public int getId_estante() {
        return id_estante;
    }

    public void setId_estante(int id_estante) {
        this.id_estante = id_estante;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return this.getNumero() + " " + this.getLetra() + " - " + this.getColor();
    }
}
