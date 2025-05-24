package com.matematicas.modelos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Banco {

    private String nombre;
    private List<Cuenta> cuentas;

    public Banco(String nombre){
        this.nombre = nombre;
        cuentas = new ArrayList<>();
    }

    public void addCuenta(Cuenta cuenta){
        this.cuentas.add(cuenta);
        cuenta.setBanco(this);
    }

    public void transferir(Cuenta origen, Cuenta destino, long monto){
        origen.debito(monto);
        destino.credito(monto);
    }
}
