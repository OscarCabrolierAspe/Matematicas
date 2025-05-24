package com.matematicas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatematicasTest {

    @Test
    @DisplayName("Test Suma")
    void testSuma(){
        Matematicas matematicas = new Matematicas();
        assertEquals(4, matematicas.sumar(2,2));     //Positivos
        assertEquals(0,matematicas.sumar(-2,2));     //Positivo + Negativo
        assertEquals(-4,matematicas.sumar(-2,-2));   //Negativos

    }

    @Test
    @DisplayName("Test restar")
    void testRestar() {
        Matematicas matematicas = new Matematicas();
        assertEquals(0, matematicas.restar(2, 2));       // Caso base
        assertEquals(-4, matematicas.restar(2, 6));      // Resultado negativo
        assertEquals(4, matematicas.restar(6, 2));       // Resultado positivo
    }

    @Test
    @DisplayName("Test multiplicar")
    void testMultiplicar() {
        Matematicas matematicas = new Matematicas();
        assertEquals(0, matematicas.multiplicar(5, 0));    // Por cero
        assertEquals(6, matematicas.multiplicar(2, 3));    // Caso normal
        assertEquals(-6, matematicas.multiplicar(-2, 3));  // Negativo por positivo
        assertEquals(6, matematicas.multiplicar(-2, -3));  // Negativo por negativo

    }

    @Test
    @DisplayName("Test dividir")
    void testDividir() {
        Matematicas matematicas = new Matematicas();
        assertEquals(2, matematicas.dividir(6, 3));    // Normal
        assertEquals(-2, matematicas.dividir(-6, 3));  // Negativo
        assertEquals(0.5, matematicas.dividir(1, 2));  // Decimales
        assertTrue(Double.isNaN(matematicas.dividir(6, 0)));    // División por cero

    }
}
