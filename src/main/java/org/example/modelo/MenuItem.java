package main.java.org.example.modelo;

public interface MenuItem {
    String getNombre();
    String getDescripcion();
    double getPrecio();
    void print(); // Método para impresión polimórfica
}