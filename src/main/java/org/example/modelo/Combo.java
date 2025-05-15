package main.java.org.example.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Combo implements MenuItem {
    private String nombre;
    private String descripcion;
    private double precioFijo;
    private List<MenuItem> items = new ArrayList<>();

    public Combo(String nombre, String descripcion, double precioFijo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioFijo = precioFijo;
    }

    public void agregarItem(MenuItem item) {
        items.add(item);
    }

    public void eliminarItem(MenuItem item) {
        items.remove(item);
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public double getPrecio() {
        return precioFijo; // Retorna el precio fijo del combo
    }

    @Override
    public void print() {
        System.out.println("== Combo: " + nombre + " ==");
        System.out.println(descripcion + " - Precio: $" + precioFijo);
        items.forEach(MenuItem::print); // Imprime los elementos del combo
    }

    public List<MenuItem> getItems() {
        return Collections.unmodifiableList(items);
    }
}