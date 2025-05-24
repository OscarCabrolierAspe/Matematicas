package com.matematicas.servicios;


import com.matematicas.modelos.Banco;
import com.matematicas.modelos.Cuenta;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioBanco {
    public void depositar(Cuenta cuenta, long monto){

        cuenta.credito(monto);
    }

    public void retirar(Cuenta cuenta, long monto){

        cuenta.debito(monto);
    }

    public long consultarSaldo(Cuenta cuenta){

        return cuenta.getSaldo();
    }

    public void transferir(Banco banco, Cuenta origen, Cuenta destino, long monto){
        banco.transferir(origen,destino,monto);
    }

    public Optional<Cuenta> buscarCuentaPorNombre(Banco banco, String nombre){
        return banco.getCuentas()
                .stream()
                .filter(cuenta -> cuenta.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }
    
}
