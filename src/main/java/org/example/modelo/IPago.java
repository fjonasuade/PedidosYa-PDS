package main.java.org.example.modelo;

public interface IPago {
    boolean procesarPago(double monto);
    String getMetodo();
}