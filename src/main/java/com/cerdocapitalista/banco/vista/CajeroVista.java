package com.cerdocapitalista.banco.vista;

import com.cerdocapitalista.banco.domain.Cuenta;
import com.cerdocapitalista.banco.persistencia.RepositorioDeCuentasPorMemoria;
import com.cerdocapitalista.banco.servicio.ServiciosDeCuenta;

import java.util.Scanner;

public class CajeroVista {
    ServiciosDeCuenta serviciosDeCuenta;
    static String USUARIO;
    public CajeroVista (ServiciosDeCuenta serviciosDeCuenta){
        this.serviciosDeCuenta = serviciosDeCuenta;
    }
    public void ejecutar(){

        while (true){
            mostrarMenu();
        }
    }
    public void mostrarMenu(){
        System.out.println("elija opción del menú");
        System.out.println("1. Log In");
        System.out.println("2. Crear Cuenta");
        System.out.print("Seleccionar Opción: ");
        Scanner scanner = new Scanner(System.in);
        String entrada = scanner.nextLine().trim();
        switch (entrada){
            case "1":{
                try {
                    logIn();
                } catch (Exception e) {
                    e.printStackTrace();
                   System.out.println("NOT YOUR ACCOUNT");
                }
                break;
            }
            case "2":{
                try {
                    crearCuenta();
                } catch (Exception e) {
                    System.out.println("hey, algo salio mal, favor de intentarlo más tarde");

                }
                break;
            }
            default:{
                System.out.println("Parametro Invalido; "+entrada);
            }
        }

    }
    public void logIn() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Usuario ");
        String usuario = scanner.nextLine().trim();
        System.out.print("Contraseña ");
        String contraseña = scanner.nextLine().trim();
        Cuenta cuentaEncontrada = serviciosDeCuenta.buscarCuenta(usuario, contraseña);
        if (cuentaEncontrada != null){
            System.out.println("Usuario Encontrado: "+cuentaEncontrada.getNombre());
            USUARIO = cuentaEncontrada.getUsuario();
            UsuarioVista menuPrincipalDeUsuario = new UsuarioVista(serviciosDeCuenta);
            menuPrincipalDeUsuario.ejecutar();
        }
        else{
            System.out.println("Credenciales Inválidas");
        }

    }
    public void crearCuenta() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Usuario ");
        String usuario = scanner.nextLine().trim();
        System.out.print("Contraseña ");
        String contraseña = scanner.nextLine().trim();
        serviciosDeCuenta.crearCuenta(nombre, usuario, contraseña);
        System.out.println("Cuenta Creada");
        System.out.print("¿Cuál es el nombre de tu chequera?: ");
        String nombreDeChequera =scanner.nextLine().trim();
        serviciosDeCuenta.crearChequera(nombreDeChequera, usuario);
        System.out.println("Chequera Creada");
    }
}
