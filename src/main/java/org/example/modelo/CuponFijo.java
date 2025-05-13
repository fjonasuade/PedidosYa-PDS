package main.java.org.example.modelo;

public class CuponFijo implements ICupon {
    private double monto;
    public CuponFijo(double monto) { this.monto = monto; }
    @Override public double aplicar(double total) {
        return total - monto;
    }
}