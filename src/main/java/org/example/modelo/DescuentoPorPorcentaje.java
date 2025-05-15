package main.java.org.example.modelo;

public class DescuentoPorPorcentaje implements ICupon {
    private double porcentaje;

    public DescuentoPorPorcentaje(double porcentaje) {
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
}