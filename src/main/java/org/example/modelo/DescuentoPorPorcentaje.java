// Para DescuentoPorPorcentaje.java
package main.java.org.example.modelo;

public class DescuentoPorPorcentaje implements ICupon {
    private String codigo;
    private double porcentaje;

    public DescuentoPorPorcentaje(String codigo, double porcentaje) {
        this.codigo = codigo;
        this.porcentaje = porcentaje;
    }

    @Override
    public double aplicar(double total) {
        return total * (1 - porcentaje/100);
    }

    @Override
    public double calcularDescuento(double total) {
        return total * (porcentaje / 100);
    }

    @Override
    public String getDescripcion() {
        return "Descuento del " + porcentaje + "%";
    }

    public String getCodigo() {
        return codigo;
    }

    public double getPorcentaje() {
        return porcentaje;
    }
}