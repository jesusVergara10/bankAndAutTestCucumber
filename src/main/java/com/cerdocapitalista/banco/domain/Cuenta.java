package com.cerdocapitalista.banco.domain;

import java.util.HashMap;
import java.util.Map;

public class Cuenta {
  private String usuario;
  private String contrasenia;
  private String nombre;
  private Map<String, Chequera> chequeras;
   public Cuenta(String usuario, String contrasenia, String nombre){
        setUsuario(usuario);
        this.contrasenia=contrasenia;
        this.nombre=nombre;
        this.chequeras =new HashMap<>();
    }
   public String getUsuario(){
        return this.usuario;
    }
    public void setUsuario(String usuario){
        if (usuario == null || usuario.length() < 0) {
            throw new RuntimeException("usuario muy corto");
        }
        this.usuario=usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Map<String, Chequera> getChequeras() {
        return chequeras;
    }

    public void setChequeras(Map<String, Chequera> chequeras) {
        this.chequeras = chequeras;
    }
    public void agregarChequera(Chequera chequera){
        this.chequeras.put(chequera.getNombre(), chequera);
    }
}
