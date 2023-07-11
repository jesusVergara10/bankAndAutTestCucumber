package com.cerdocapitalista.banco.persistencia;

import com.cerdocapitalista.banco.domain.Cuenta;

public interface RepositorioDeCuentas {
    Cuenta buscarCuentaPorUsuario(String usuario);
    void persistirCuenta(Cuenta cuenta);
    void borrarCuenta(String usuario);
}
