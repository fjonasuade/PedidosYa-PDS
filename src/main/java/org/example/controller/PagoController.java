package main.java.org.example.controller;

import main.java.org.example.modelo.IPago;
import main.java.org.example.modelo.Pedido;

public class PagoController {
    public boolean procesarPago(Pedido pedido, IPago pago) {
        if (pago.procesarPago(pedido.getTotal())) {
            System.out.println("Pago exitoso con " + pago.getMetodo() + " por $" + pedido.getTotal());
            pedido.generarFactura();
            return true;
        } else {
            System.out.println("Pago rechazado. Monto insuficiente.");
            return false;
        }
    }
}