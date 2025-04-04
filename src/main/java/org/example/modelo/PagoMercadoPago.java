package main.java.org.example.modelo;

public class PagoMercadoPago implements IPago{
    private double monto;

    public PagoMercadoPago(double monto) {
        this.monto = monto;
    }

    @Override
    public boolean procesarPago(double monto) {
        // Lógica para procesar pago con MercadoPago
        return true;
    }

    @Override
    public String getMetodo() {
        return "MercadoPago";
    }
}
