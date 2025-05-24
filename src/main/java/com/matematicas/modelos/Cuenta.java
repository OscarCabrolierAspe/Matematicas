package com.matematicas.modelos;


import com.matematicas.exceptions.SaldoInsuficienteException;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Cuenta {

    private String nombre;
    private long saldo;
    private Banco banco;

    public Cuenta(String nombre, long saldo){
        this.nombre = nombre;
        this.saldo = saldo;
    }
    public void credito(long monto){
        this.saldo += monto;
    }

    public void debito(long monto){
        if (monto > this.saldo){
            throw new SaldoInsuficienteException("saldo insuficiente");
        }
        this.saldo -= monto;
    }
}
