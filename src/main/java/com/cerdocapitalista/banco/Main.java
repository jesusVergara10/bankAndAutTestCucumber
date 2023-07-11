package com.cerdocapitalista.banco;

import com.cerdocapitalista.banco.persistencia.RepositorioDeCuentasPorArchivo;
import com.cerdocapitalista.banco.persistencia.RepositorioDeCuentasPorMemoria;
import com.cerdocapitalista.banco.servicio.ServiciosDeCuenta;
import com.cerdocapitalista.banco.vista.CajeroVista;

public class Main {

    public static void main(String[] args) {
        RepositorioDeCuentasPorArchivo repositorioDeCuentasPorArchivo = new RepositorioDeCuentasPorArchivo("/Users/nenenney.chuy/IdeaProjects/bankatm/Archivos/");
        ServiciosDeCuenta serviciosDeCuenta = new ServiciosDeCuenta(repositorioDeCuentasPorArchivo);
        CajeroVista primeraPantalla = new CajeroVista(serviciosDeCuenta);
        primeraPantalla.ejecutar();
        MiClaseDaIgual.cambiarValorDeIncrementador(10);


       try{
           MiClaseDaIgual clase1 = new MiClaseDaIgual(1);
           System.out.println(clase1.incrementarNumero(1));
       }finally{}

       try {
           MiClaseDaIgual clase2 = new MiClaseDaIgual(2);
           System.out.println(clase2.incrementarNumero(1));
       }finally {}
//        MiClaseDaIgual.incrementarNumero();
//        System.out.println(MiClaseEstaticaDaIgual.incrementarNumero(2));
//        System.out.println(MiClaseEstaticaDaIgual.incrementarNumero(4));


    }
}
