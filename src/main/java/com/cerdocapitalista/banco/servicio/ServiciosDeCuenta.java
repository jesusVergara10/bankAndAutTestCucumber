package com.cerdocapitalista.banco.servicio;

import com.cerdocapitalista.banco.domain.Chequera;
import com.cerdocapitalista.banco.domain.Cuenta;
import com.cerdocapitalista.banco.persistencia.RepositorioDeCuentas;

import java.util.Map;

public class ServiciosDeCuenta {
    private RepositorioDeCuentas repositorioDeCuentas;

    public ServiciosDeCuenta (RepositorioDeCuentas repositorioDeCuentas){
        this.repositorioDeCuentas = repositorioDeCuentas;
    }

    public void crearCuenta (String nombre, String usuario, String contrasenia) throws Exception {
        Cuenta nuevoCuenta = new Cuenta(usuario, contrasenia, nombre);
        Cuenta usuarioEncontrado = buscarCuenta(usuario);
        if(usuarioEncontrado == null){
            repositorioDeCuentas.persistirCuenta(nuevoCuenta);
        }else{
            throw new RuntimeException("Ya existe una cuenta con ese username");
        }


    }

    public Cuenta buscarCuenta (String usuario, String contrasenia) throws Exception {
        Cuenta usuarioEncontrado = repositorioDeCuentas.buscarCuentaPorUsuario(usuario);
        if (usuarioEncontrado != null){
            if (usuarioEncontrado.getContrasenia().equals(contrasenia)){
                return usuarioEncontrado;
            }
        }
        return null;
    }
    public Cuenta buscarCuenta (String usuario) throws Exception {
        Cuenta usuarioEncontrado = repositorioDeCuentas.buscarCuentaPorUsuario(usuario);
        if (usuarioEncontrado != null){
                return usuarioEncontrado;
        }
        return null;
    }



    public void crearChequera(String nombre, String usuario) throws Exception {
        Chequera nuevaChequera = new Chequera(nombre);
        Cuenta cuentaEncontrada = repositorioDeCuentas.buscarCuentaPorUsuario(usuario);
        cuentaEncontrada.agregarChequera(nuevaChequera);
        repositorioDeCuentas.persistirCuenta(cuentaEncontrada);
    }

    public void depositarDineroAChequera(String nombreDeChequera, Double cantidadDeDineroADepositar, String usuario) throws Exception {
        Cuenta cuentaEncontrado = repositorioDeCuentas.buscarCuentaPorUsuario(usuario);
        Map <String, Chequera> mapaDeCuentas = cuentaEncontrado.getChequeras();
        Chequera chequeraEncontrada = mapaDeCuentas.get(nombreDeChequera);
        chequeraEncontrada.setDineroDisponible(cantidadDeDineroADepositar + chequeraEncontrada.getDineroDisponible());
        repositorioDeCuentas.persistirCuenta(cuentaEncontrado);
    }

    public Boolean retirarDineroDeChequera(String nombreDeCuenta, Double cantidadDeDineroARetirar, String usuario) {
        Cuenta cuentaEncontrado = repositorioDeCuentas.buscarCuentaPorUsuario(usuario);
        Map <String, Chequera> mapaDeCuentas = cuentaEncontrado.getChequeras();
        Chequera chequeraEncontrada = mapaDeCuentas.get(nombreDeCuenta);
        if(chequeraEncontrada.getDineroDisponible()<cantidadDeDineroARetirar){
            throw new RetiroInsuficienteException("dinero insuficiente, dinero disponible: " + chequeraEncontrada.getDineroDisponible() + " cantidad que quieres retirar: " + cantidadDeDineroARetirar);

        }
        else{
            chequeraEncontrada.setDineroDisponible(chequeraEncontrada.getDineroDisponible() - cantidadDeDineroARetirar);
            repositorioDeCuentas.persistirCuenta(cuentaEncontrado);
            return true;
        }
    }
}
