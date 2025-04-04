package main.java.org.example.modelo;

public class PagoEfectivo implements IPago{
    private double monto;

    public PagoEfectivo(double monto) {
        this.monto = monto;
    }

    @Override
    public boolean procesarPago(double monto) {
        // Lógica para procesar pago en efectivo
        return true;
    }

    @Override
    public String getMetodo() {
        return "Efectivo";
    }
}
