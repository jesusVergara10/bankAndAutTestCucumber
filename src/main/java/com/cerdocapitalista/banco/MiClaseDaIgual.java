package com.cerdocapitalista.banco;

public class MiClaseDaIgual {
    int miNumero;
    static int incrementadorMayor = 0;

    public  MiClaseDaIgual (int comoSea){
        this.miNumero = comoSea;
    }

    public int incrementarNumero (int valorAIncrementar){
        miNumero = miNumero + valorAIncrementar + incrementadorMayor;
        return miNumero;
    }

    public static void cambiarValorDeIncrementador(int whatevs){
        incrementadorMayor = whatevs;
    }
}
