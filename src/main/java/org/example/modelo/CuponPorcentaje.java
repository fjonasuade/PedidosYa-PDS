package main.java.org.example.modelo;

public class CuponPorcentaje implements ICupon {
    private double porcentaje;
    public CuponPorcentaje(double porcentaje) { this.porcentaje = porcentaje; }
    @Override public double aplicar(double total) {
        return total * (1 - porcentaje/100);
    }
}