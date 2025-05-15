package main.java.org.example.modelo;

public class CuponFijo implements ICupon {
    private double monto;

    public CuponFijo(double monto) {
        this.monto = monto;
    }

    @Override
    public double aplicar(double total) {
        return total - monto;
    }

    @Override
    public double calcularDescuento(double total) {
        return Math.min(monto, total); // El descuento no puede ser mayor que el total
    }

    @Override
    public String getDescripcion() {
        return "Cupón fijo de $" + monto;
    }
}