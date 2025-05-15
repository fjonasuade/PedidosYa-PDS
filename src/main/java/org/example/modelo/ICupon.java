package main.java.org.example.modelo;

public interface ICupon {
    double aplicar(double total);

    double calcularDescuento(double total); // Retorna el monto del descuento
    String getDescripcion(); // Describe el tipo de descuento
}