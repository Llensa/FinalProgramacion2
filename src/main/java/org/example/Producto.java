package org.example;

public class Producto {
    private int id;
    private String nombre;
    private String categoria;
    private int cantidad;
    private double precio;

    public Producto(int id, String nombre, String categoria, int cantidad, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Producto(String nombre, String categoria, int cantidad, double precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precio = precio;
    }


}
