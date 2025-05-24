package com.matematicas.servicios;

import com.matematicas.modelos.Banco;
import com.matematicas.modelos.Cuenta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ServicioBancoTest {

    @Test
    @DisplayName("Test transferir dinero")
    void testTransferirDineroCuentas (){
        Cuenta cuentaOrigen = new Cuenta("Oscar",45677);
        Cuenta cuentaDestino = new Cuenta("Pedro",34567);

        Banco banco = new Banco("Banco Estado");

        long transferencia = 2000;
        banco.transferir(cuentaOrigen,cuentaDestino,transferencia);

        assertEquals(43677,cuentaOrigen.getSaldo());
        assertEquals(36567,cuentaDestino.getSaldo());
    }

    @Test
    @DisplayName("Test Relacion Cuentas")
    void testRelacionCuentas(){
        Cuenta cuentaOrigen = new Cuenta("Oscar",45677);
        Cuenta cuentaDestino = new Cuenta("Pedro", 34567);

        Banco banco = new Banco("Banco Estado");
        banco.addCuenta(cuentaOrigen);
        banco.addCuenta(cuentaDestino);

        assertEquals(2,banco.getCuentas().size());
        assertEquals("Banco Estado",cuentaOrigen.getBanco().getNombre());
        assertEquals("Oscar",banco.getCuentas()
                .stream()
                .filter(c -> c.getNombre().equals("Oscar"))
                .findFirst().get().getNombre());
    }

    @Test
    @DisplayName("Test buscar cuenta por nombre usando ServicioBanco")
    void testBuscarCuentaPorNombre() {
        Banco banco = new Banco("Banco Estado");
        Cuenta cuenta1 = new Cuenta("Oscar", 45677);
        Cuenta cuenta2 = new Cuenta("Pedro", 34567);

        banco.addCuenta(cuenta1);
        banco.addCuenta(cuenta2);

        ServicioBanco servicio = new ServicioBanco();
        Optional<Cuenta> resultado = servicio.buscarCuentaPorNombre(banco, "Oscar");

        assertTrue(resultado.isPresent());
        assertEquals("Oscar", resultado.get().getNombre());
    }
}