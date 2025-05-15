package main.java.org.example.modelo;

public class DescuentoFijo implements ICupon {
    private double montoFijo;

    public DescuentoFijo(double montoFijo) {
        this.montoFijo = montoFijo;
    }

    @Override
    public double aplicar(double total) {
        // Devuelve el total después de aplicar el descuento fijo
        return total - Math.min(total, montoFijo);
    }

    @Override
    public double calcularDescuento(double total) {
        return Math.min(total, montoFijo); // El descuento no puede ser mayor al total
    }

    @Override
    public String getDescripcion() {
        return "Descuento fijo de $" + montoFijo;
    }
}