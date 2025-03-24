package main.java.org.example.modelo;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Categoria> categorias = new ArrayList<>();

    public void agregarCategoria(Categoria categoria) { categorias.add(categoria); }
    public List<Categoria> getCategorias() { return categorias; }
}
