package main.java.org.example.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private String nombre;
    private List<Plato> platos = new ArrayList<>();

    public Categoria(String nombre) { this.nombre = nombre; }

    public void agregarPlato(Plato plato) { platos.add(plato); }
    public List<Plato> getPlatos() { return platos; }
}
