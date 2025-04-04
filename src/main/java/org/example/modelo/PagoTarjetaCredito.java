package main.java.org.example.modelo;

public class PagoTarjetaCredito implements IPago{
    private double monto;

    public PagoTarjetaCredito(double monto) {
        this.monto = monto;
    }

    @Override
    public boolean procesarPago(double monto) {
        // Lógica para procesar pago con tarjeta de crédito
        return true;
    }

    @Override
    public String getMetodo() {
        return "Tarjeta de Crédito";
    }
}
