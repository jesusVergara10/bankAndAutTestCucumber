package com.cerdocapitalista.banco.domain;

public class Chequera {
    Double dineroDisponible;
    String nombre;

    public Chequera(String nombre) {
        this.nombre = nombre;
        this.dineroDisponible = 0.0;
    }
    public Chequera(String nombre, Double dineroDisponible){
        this.nombre = nombre;
        this.dineroDisponible = dineroDisponible;
    }

    public Double getDineroDisponible() {
        return dineroDisponible;
    }

    public void setDineroDisponible(Double dineroDisponible) {
        this.dineroDisponible = dineroDisponible;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Chequera{" +
                "dineroDisponible=" + dineroDisponible +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
