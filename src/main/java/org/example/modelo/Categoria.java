package main.java.org.example.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria implements MenuItem {
    private String nombre;
    private List<MenuItem> items = new ArrayList<>();

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public void agregarItem(MenuItem item) {
        items.add(item);
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void agregarPlato(Plato plato) {
        items.add(plato);
    }

    public List<Plato> getPlatos() {
        List<Plato> platos = new ArrayList<>();
        for (MenuItem item : items) {
            if (item instanceof Plato) {
                platos.add((Plato) item);
            }
        }
        return platos;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getDescripcion() {
        return "Categoría: " + nombre;
    }

    @Override
    public double getPrecio() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrecio();
        }
        return total;
    }

    @Override
    public void print() {
        System.out.println("=== " + nombre + " ===");
        for (MenuItem item : items) {
            item.print();
        }
    }
}