package main.java.org.example.modelo;

public class PagoTarjetaDebito implements IPago {
    private double monto;

    public PagoTarjetaDebito(double monto) {
        this.monto = monto;
    }

    @Override
    public boolean procesarPago(double monto) {
        // Lógica para procesar pago con tarjeta de débito
        return true;
    }

    @Override
    public String getMetodo() {
        return "Tarjeta de Débito"; // Corregido de "Tarjeta de Crédito"
    }
}