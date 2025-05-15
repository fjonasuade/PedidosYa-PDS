package main.java.org.example.modelo;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private static Menu instancia; // Instancia única
    private List<Categoria> categorias;

    private Menu() {
        categorias = new ArrayList<>();
    }

    public static Menu getInstancia() {
        if (instancia == null) {
            instancia = new Menu();
        }
        return instancia;
    }

    public void agregarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void imprimirMenu() {
    }
}