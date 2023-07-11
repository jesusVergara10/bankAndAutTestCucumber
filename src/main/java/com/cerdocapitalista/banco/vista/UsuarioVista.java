package com.cerdocapitalista.banco.vista;

import com.cerdocapitalista.banco.VistaException;
import com.cerdocapitalista.banco.domain.Cuenta;
import com.cerdocapitalista.banco.servicio.RetiroInsuficienteException;
import com.cerdocapitalista.banco.servicio.ServiciosDeCuenta;

import java.util.Scanner;

public class UsuarioVista {
    ServiciosDeCuenta serviciosDeCuenta;
    public UsuarioVista (ServiciosDeCuenta serviciosDeCuenta){
        this.serviciosDeCuenta = serviciosDeCuenta;
    }
    void ejecutar(){
        Boolean ejecutarMenu = true;
        while (ejecutarMenu){
            ejecutarMenu = mostrarMenuUsuario();
        }
    }
    public Boolean mostrarMenuUsuario(){
        System.out.println("elija opción del menú");
        System.out.println("1. Depositar Dinero");
        System.out.println("2. Retirar Dinero");
        System.out.println("3. Imprimir chequeras");
        System.out.println("4. Log Out");
        System.out.print("Seleccionar Opción: ");
        Scanner scanner = new Scanner(System.in);
        String entrada = scanner.nextLine().trim();
        switch (entrada){
            case "1":{
                try {
                    depositarDineroMenu();
                } catch (Exception e) {
                   System.out.println("no, no, no, you deepshit, así no. Try again later." + e);
                }
                break;
            }
            case "2":{
                try {
                    retirarDineroMenu();
                } catch (Exception e) {
                   System.out.println("hey shithead, try again later" + e);
                }
                break;
            }
            case "3":{
                try {
                    imprimirChequera();
                } catch (Exception e) {
                    System.out.println("no lo mereces");
                }
                break;
            }
            case "4":{
                return false;
            }
            default:{
                System.out.println("Parametro Invalido; "+entrada);
            }
        }
        return true;

    }
    public void depositarDineroMenu () throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introdusca nombre de chequera ");
        String nombreDeChequera =  scanner.nextLine();
        System.out.print("Cantidad de dinero a depositar ");
       try {
           Double dineroADepositar = Double.parseDouble(scanner.nextLine());
           serviciosDeCuenta.depositarDineroAChequera(nombreDeChequera, dineroADepositar, CajeroVista.USUARIO);
       } catch (Exception e){
           throw new VistaException("Oye, algo salió mal a la hora de intentar depositar dinero", e);
       }
    }

    public void retirarDineroMenu() {
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introdusca nombre de chequera ");
            String nombreDeChequera = scanner.nextLine();
            System.out.print("Dinero a retirar ");
            Double dineroARetirar = Double.parseDouble(scanner.nextLine());
            serviciosDeCuenta.retirarDineroDeChequera(nombreDeChequera,dineroARetirar,CajeroVista.USUARIO);
        } catch (RetiroInsuficienteException e){
            System.out.println(e.getMessage());
        } catch (Exception e){
            throw new VistaException(e);
        } finally {
            System.out.println("estoy dentro del finally");
        }

    }

    public void imprimirChequera() throws Exception {
        Cuenta cuentaEncontrada = serviciosDeCuenta.buscarCuenta(CajeroVista.USUARIO);
        System.out.println(cuentaEncontrada.getChequeras());
    }

}
