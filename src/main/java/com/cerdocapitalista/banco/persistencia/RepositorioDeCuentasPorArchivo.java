package com.cerdocapitalista.banco.persistencia;

import com.cerdocapitalista.banco.domain.Chequera;
import com.cerdocapitalista.banco.domain.Cuenta;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RepositorioDeCuentasPorArchivo implements RepositorioDeCuentas {
    String direccionDeCarpeta;

    public RepositorioDeCuentasPorArchivo (String direccionDeCarpeta){
        this.direccionDeCarpeta = direccionDeCarpeta;
    }

    @Override
    public Cuenta buscarCuentaPorUsuario(String usuario){
        Map<String, Cuenta> mapaDeCuentas = null;
        try {
            mapaDeCuentas = leerArchivo(direccionDeCarpeta);
        } catch (Exception e) {
            throw new RuntimeException(e);       
        }
        return mapaDeCuentas.get(usuario);
    }

    @Override
    public void persistirCuenta(Cuenta cuenta){
        try {
            Map<String, Cuenta> mapaDeCuentas = leerArchivo(direccionDeCarpeta);
            mapaDeCuentas.put(cuenta.getUsuario(), cuenta);
            grabarCuentasEnArchivos(mapaDeCuentas, direccionDeCarpeta);
        } catch (Exception e) {
            System.out.println("paso un error");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void borrarCuenta(String usuario) {
        try {
           Map<String, Cuenta> mapaDeCuentas = leerArchivo(direccionDeCarpeta);
            mapaDeCuentas.remove(usuario);
            grabarCuentasEnArchivos(mapaDeCuentas, direccionDeCarpeta);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

     Map <String, Cuenta> leerArchivo (String direccionDeCarpeta) throws Exception {
        if (direccionDeCarpeta==null){
            throw new RuntimeException("dirección de carpeta vacio");
        }
        File cuentasFile = new File(direccionDeCarpeta+"Cuentas.csv");
        cuentasFile.createNewFile();
        BufferedReader cuentasReader = new BufferedReader(new FileReader(cuentasFile));
        Map <String, Cuenta> mapaDeCuentas = new HashMap<>();

        String cuentaslinea = cuentasReader.readLine();
        while (cuentaslinea!=null){
            String[] infoDeCuenta = cuentaslinea.split(",");
            String nombreDeUsuario = infoDeCuenta[0];
            String contrasenia = infoDeCuenta[1];
            String nombreDePersona = infoDeCuenta[2];
            Cuenta cuenta = new Cuenta(nombreDeUsuario, contrasenia, nombreDePersona);
            mapaDeCuentas.put(nombreDeUsuario, cuenta);
            cuentaslinea=cuentasReader.readLine();
        }
        File chequerasFile = new File(direccionDeCarpeta+"Chequeras.csv");
        chequerasFile.createNewFile();
        BufferedReader chequeraReader = new BufferedReader(new FileReader(chequerasFile));
        String chequeraLinea = chequeraReader.readLine();
        while (chequeraLinea != null) {
            String[] infoDeChequera = chequeraLinea.split(",");
            String nombreDeUsuario = infoDeChequera[0];
            String nombreDeChequera = infoDeChequera[1];
            String dineroDisponible = infoDeChequera[2];
            Chequera chequera = new Chequera(nombreDeChequera, Double.parseDouble(dineroDisponible));

            Cuenta cuentaEncontrada = mapaDeCuentas.get(nombreDeUsuario);
            cuentaEncontrada.agregarChequera(chequera);

            chequeraLinea=chequeraReader.readLine();
        }

         return mapaDeCuentas;
     }
     void grabarCuentasEnArchivos (Map <String, Cuenta> cuentas,String direccionDeCarpeta)throws Exception {
         BufferedWriter cuentasWriter = new BufferedWriter(new FileWriter(direccionDeCarpeta+"Cuentas.csv" ));
         BufferedWriter chequerasWriter = new BufferedWriter(new FileWriter(direccionDeCarpeta+"Chequeras.csv" ));
         for (Map.Entry<String, Cuenta> cuentasAGrabar: cuentas.entrySet()){
            Cuenta cuentaAGrabar = cuentasAGrabar.getValue();
            String valorDelCsv = cuentaAGrabar.getUsuario() + "," + cuentaAGrabar.getContrasenia() + "," + cuentaAGrabar.getNombre() + "\n";
            cuentasWriter.append(valorDelCsv);
             for (Map.Entry<String, Chequera> chequerasAGrabar: cuentaAGrabar.getChequeras().entrySet()){
                 Chequera chequeraAGrabar = chequerasAGrabar.getValue();
                 String valorDelCsvDeChequera = cuentaAGrabar.getUsuario() + "," + chequeraAGrabar.getNombre() + "," + chequeraAGrabar.getDineroDisponible() + "\n";
                 chequerasWriter.append(valorDelCsvDeChequera);
             }
         }
         cuentasWriter.close();
         chequerasWriter.close();
     }

}
