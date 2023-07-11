package com.cerdocapitalista.banco;

public class MiClaseEstaticaDaIgual {
    static int miNumero = 0;

    public static int incrementarNumero (int valor){
        miNumero=miNumero+valor;
        return miNumero;
    }
}
