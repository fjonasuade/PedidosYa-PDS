package main.java.org.example.modelo;

import java.util.ArrayList;
import java.util.List;

public class BaseDeDatos {
    private static List<Plato> platos = new ArrayList<>();

    public static void agregarPlato(Plato plato) {
        platos.add(plato);
    }

    public static void eliminarPlato(String nombre) {
        platos.removeIf(plato -> plato.getNombre().equals(nombre));
    }

    public static List<Plato> getPlatos() {
        return platos;
    }
}