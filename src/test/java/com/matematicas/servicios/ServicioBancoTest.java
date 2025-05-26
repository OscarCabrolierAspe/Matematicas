package com.matematicas.servicios;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.matematicas.exceptions.SaldoInsuficienteException;
import com.matematicas.modelos.Banco;
import com.matematicas.modelos.Cuenta;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class ServicioBancoTest {

    /* Objetos a usar = Variables que se usaran en las pruebas*/
    ServicioBanco servicio;
    Cuenta cuentaOrigen;
    Cuenta cuentaDestino;
    Banco banco;

    /* Ejecutado una vez antes de todas las prueba. Debe ser static*/
    @BeforeAll
    static void initAll(){
        System.out.println("<----INICIANDO TODOS LOS TESTS---->");
    }

    /* Ejecutado una vez después de todas las pruebas*/
    @AfterAll
    static void tearDownall(){
        System.out.println("<----FINALIZANDO TODOS LOS TESTS---->");
    }

    /*@BeforeEach ejecutado antes de cada prueba individual*/
    @BeforeEach
    void init(){

        /*Crea cuentas, banco y enlaza las cuentas al banco*/
        servicio = new ServicioBanco();
        cuentaOrigen = new Cuenta("Oscar",45677);
        cuentaDestino = new Cuenta("Pedro",34567);
        banco = new Banco("Banco Estado");
        banco.addCuenta(cuentaOrigen);
        banco.addCuenta(cuentaDestino);
        cuentaOrigen.setBanco(banco);
        cuentaDestino.setBanco(banco);
    }


    /*Ejecutado después de cada prueba*/
    @AfterEach
    void tearDown(){
        System.out.println("<----Test Finalizado---->");
    }

    /*Anidado de tests de transferencias*/
    @Nested
    @DisplayName("Test de Transferencias")
    @Tag("transferencia")
    class TransferenciasTests{

        /*Verifica que al transferir 2000 desde cuentaOrigen, los saldos se actualicen correctamente*/
        @Test
        @DisplayName("Transferencia exitosa")
        void testTransferenciaExitosa(){
            servicio.transferir(banco,cuentaOrigen,cuentaDestino, 2000);
            assertEquals(43677, cuentaOrigen.getSaldo());
            assertEquals(36567,cuentaDestino.getSaldo());
        }

        /*Verifica que se lance una excepción cuando se intenta transferir más dinero del que hay*/
        @Test
        @DisplayName("Transferencia con saldo insuficiente")
        void testTransferenciaConSaldoInsuficiente(){
            assertThrows(SaldoInsuficienteException.class, () ->
                    servicio.transferir(banco,cuentaOrigen, cuentaDestino, 999999));
        }
    }

    /*Anidado de tests de Deposito y retiro*/
    @Nested
    @DisplayName("Tests de Deposito y Retiro")
    @Tag("Movimientos")
    class MovimientosTests{

        /*Deposita 5000 y verifica el nuevo saldo*/
        @Test
        @DisplayName("Depositar dinero a la cuenta")
        void testDepositar(){
            servicio.depositar(cuentaOrigen, 5000);
            assertEquals(50677,cuentaOrigen.getSaldo());
        }

        /*Retira 567 de cuentaDestino y verifica el saldo*/
        @Test
        @DisplayName("Retirar dinero de la cuenta")
        void testRetirar(){
            servicio.retirar(cuentaDestino,567);
            assertEquals(34000,cuentaDestino.getSaldo());
        }

        /*Verifica que se lance excepción si el retiro es mayor al saldo disponible*/
        @Test
        @DisplayName("Retirar con saldo insuficiente lanza excepción")
        void testRetirarSaldoInsuficiente(){
            assertThrows(SaldoInsuficienteException.class,()-> servicio.retirar(cuentaDestino,999999));
        }
    }

    /*Anidado de consultas de cuentas*/
    @Nested
    @DisplayName("Consultar saldo de una cuenta")
    class ConsultarTests{

        /*Verifica el saldo inicial de la cuentaOrigen*/
        @Test
        @DisplayName("Consultar saldo de una cuenta")
        void testConsultarSaldo(){
            assertEquals(45677,servicio.consultarSaldo(cuentaOrigen));
        }

        /*Búsqueda de la cuenta por nombre (ignora mayúsculas/minúsculas)y verifica que fue encontrada correctamente*/
        @Test
        @DisplayName("Buscar cuenta por nombre")
        void  testBuscarCuentaPorNombre(){
            Optional<Cuenta> resultado =servicio.buscarCuentaPorNombre(banco, "oscar");
            assertTrue(resultado.isPresent());
            assertEquals("Oscar", resultado.get().getNombre());
        }

        /*Verificación que si no existe la cuenta, se devuelva un Optional.empty()*/
        @Test
        @DisplayName("Buscar cuenta inexistente")
        void testBuscarCuentaExistente(){
            Optional<Cuenta> resultado = servicio.buscarCuentaPorNombre(banco, "Alejandro");
            assertFalse(resultado.isPresent());
        }

    }

    @Nested
    @DisplayName("Test de Nulos y Asserts Adicionales")
    @Tag("extras")
    class ExtrasTests{

        /*Verficacion de que cuentaOrigen no sea null*/
        @Test
        @DisplayName("Validad que una cuenta no sea nula")
        void testCuentaNula(){
            assertNotNull(cuentaOrigen);
        }

        /*Verifica que buscar una cuenta no existente retorna un Optional vacío*/
        @Test
        @DisplayName("Validar que búsqueda de una cuenta inexistente sea null (optional empty)")
        void testCuentaNull(){
            Optional<Cuenta> cuenta = servicio.buscarCuentaPorNombre(banco, "Desconocido");
            assertTrue(cuenta.isEmpty());
        }
    }


}