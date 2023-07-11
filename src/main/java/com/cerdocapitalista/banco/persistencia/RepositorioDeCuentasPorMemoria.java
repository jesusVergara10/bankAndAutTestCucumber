package com.cerdocapitalista.banco.persistencia;

import com.cerdocapitalista.banco.domain.Cuenta;

import java.util.HashMap;
import java.util.Map;

public class RepositorioDeCuentasPorMemoria implements RepositorioDeCuentas {
   private Map <String, Cuenta> usuarios = new HashMap<>();

    @Override
    public Cuenta buscarCuentaPorUsuario(String usuario) {
        return usuarios.get(usuario);
    }

    @Override
    public void persistirCuenta(Cuenta cuenta) {
        usuarios.put(cuenta.getUsuario(), cuenta);
    }

    @Override
    public void borrarCuenta(String usuario) {
        usuarios.remove(usuario);
    }
}
