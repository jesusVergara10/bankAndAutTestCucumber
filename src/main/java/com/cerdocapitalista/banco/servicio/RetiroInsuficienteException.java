package com.cerdocapitalista.banco.servicio;

public class RetiroInsuficienteException extends RuntimeException{
    public RetiroInsuficienteException(String message) {
        super(message);
    }
}

