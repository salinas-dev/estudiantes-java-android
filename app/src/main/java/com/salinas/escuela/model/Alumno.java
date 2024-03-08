package com.salinas.escuela.model;

public class Alumno {
    private String nombre;
    private String nivel;
    private double promedio;
    private int cantidadMaterias;

    public Alumno(String nombre, String nivel, double promedio, int cantidadMaterias) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.promedio = promedio;
        this.cantidadMaterias = cantidadMaterias;
    }

    public double calcularColegiatura() {
        double precioMateria = 0;
        int maxMaterias = 0;
        switch(nivel) {
            case "secundaria":
                precioMateria = 180;
                maxMaterias = 8;
                break;
            case "bachillerato":
                precioMateria = 250;
                maxMaterias = 7;
                break;
            case "universidad":
                precioMateria = 420;
                maxMaterias = 6;
                break;
            default:
                System.out.println("Nivel de alumno inválido.");
                return 0;
        }
        double colegiatura = Math.min(cantidadMaterias, maxMaterias) * precioMateria;
        return colegiatura;
    }

    public double calcularDescuento() {
        double descuento = 0;
        if (promedio >= 9.5) {
            descuento = 0.3;
        } else if (promedio >= 9 && promedio < 9.5) {
            descuento = 0.15;
        }
        return descuento * calcularColegiatura();
    }

    public double calcularIVA() {
        double iva = 0;
        if (promedio < 8) {
            iva = 0.16;
        }
        return iva * calcularColegiatura();
    }

    public double calcularTotal() {
        double total = calcularColegiatura() - calcularDescuento() + calcularIVA();
        return total;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public double getPromedio() {
        return promedio;
    }

    public int getCantidadMaterias() {
        return cantidadMaterias;
    }
}
