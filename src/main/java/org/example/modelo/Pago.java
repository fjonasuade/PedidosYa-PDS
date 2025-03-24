package main.java.org.example.modelo;

public class Pago {
    private String metodo;
    private double monto;

    public Pago(String metodo, double monto) {
        this.metodo = metodo;
        this.monto = monto;
    }

    public String getMetodo() { return metodo; }
    public double getMonto() { return monto; }
}
