package com.matematicas;

public class Matematicas {
    public double sumar(double a, double b) {
        return a + b;
    }

    public double restar(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    public double dividir(double a, double b) {
        if (b == 0) {
            System.out.println("Advertencia: No se puede dividir por cero.");
            return Double.NaN; // Resonator "Not a Number"
        }
        return a / b;
    }
}
