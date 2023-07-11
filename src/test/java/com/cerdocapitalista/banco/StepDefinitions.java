package com.cerdocapitalista.banco;

import com.cerdocapitalista.banco.domain.Chequera;
import com.cerdocapitalista.banco.domain.Cuenta;
import com.cerdocapitalista.banco.persistencia.RepositorioDeCuentasPorMemoria;
import com.cerdocapitalista.banco.servicio.ServiciosDeCuenta;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class StepDefinitions {

    private ServiciosDeCuenta serviciosDeCuenta = null;
    private String errorActual = "";

    @Before
    public void init(){
        RepositorioDeCuentasPorMemoria repositorioDeCuentasPorMemoria = new RepositorioDeCuentasPorMemoria();
        this.serviciosDeCuenta = new ServiciosDeCuenta(repositorioDeCuentasPorMemoria);
    }

    @When("cree una cuenta nueva de nombre {string}, usuario {string} y contraseña {string}")
    public void cuando_cree_una_cuenta_nueva(String name,String username, String password) throws Exception {
       try{
           this.serviciosDeCuenta.crearCuenta(name,username,password);
       }catch (Exception e){
           this.errorActual=e.getMessage();
       }
    }

    @Then("deberia poder encontrar una cuenta a nombre de {string}")
    public void encontrando_la_cuenta_que_cree(String username) throws Exception {
        Cuenta usuarioEncontrado = this.serviciosDeCuenta.buscarCuenta(username);
        Assertions.assertNotNull(usuarioEncontrado);
    }

    @Then("Hubo un error tipo:{string}")
    public void mensajeErrorActual(String error){
        Assertions.assertEquals(error, this.errorActual);
    }

    @Then("crea una chequera con el nombre {string} para la cuenta de username {string}")
    public void crearChequera(String nombreDeChequera, String username) throws Exception {
        this.serviciosDeCuenta.crearChequera(nombreDeChequera, username);
    }

    @Then("depositar {string} a la cuenta de nombre {string} con el username {string}")
    public void depositarDineroEnChequera(String dinero, String nombreCuenta, String username) throws Exception {
        this.serviciosDeCuenta.depositarDineroAChequera(nombreCuenta,Double.parseDouble(dinero), username);

    }

    @Then("verificar que haya {string} disponibles en chequera llamada {string} de usuario {string}")
    public void verificarQueHayaDisponiblesEnChequeraLlamadaDeUsuario(String arg0, String arg1, String arg2) throws Exception {
        Cuenta cuentaEncontrada = this.serviciosDeCuenta.buscarCuenta(arg2);
        Chequera chequeraEncontrada = cuentaEncontrada.getChequeras().get(arg1);
        Assertions.assertEquals(Double.parseDouble(arg0),chequeraEncontrada.getDineroDisponible());
    }

    @And("retirar {string} a la cuenta de nombre {string} con el username {string}")
    public void retirarALaCuentaDeNombreConElUsername(String dinero, String nombreCuenta, String username) {
        try{
            this.serviciosDeCuenta.retirarDineroDeChequera(nombreCuenta,Double.parseDouble(dinero), username);
        }catch (Exception e){
            this.errorActual=e.getMessage();
        }

    }
}
