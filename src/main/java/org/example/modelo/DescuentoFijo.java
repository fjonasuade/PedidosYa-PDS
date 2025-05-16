// Para DescuentoFijo.java
package main.java.org.example.modelo;

public class DescuentoFijo implements ICupon {
    private String codigo;
    private double montoFijo;

    public DescuentoFijo(String codigo, double montoFijo) {
        this.codigo = codigo;
        this.montoFijo = montoFijo;
    }

    @Override
    public double aplicar(double total) {
        return total - Math.min(total, montoFijo);
    }

    @Override
    public double calcularDescuento(double total) {
        return Math.min(total, montoFijo);
    }

    @Override
    public String getDescripcion() {
        return "Descuento fijo de $" + montoFijo;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getMontoFijo() {
        return montoFijo;
    }
}