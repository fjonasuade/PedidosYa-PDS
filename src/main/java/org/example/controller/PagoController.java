package main.java.org.example.controller;

import main.java.org.example.modelo.Cliente;
import main.java.org.example.modelo.Factura;
import main.java.org.example.modelo.Pago;
import main.java.org.example.modelo.Pedido;

public class PagoController {
    public boolean procesarPago(Pedido pedido, Pago pago, Cliente cliente) {
        if (pago.getMonto() >= pedido.getTotal()) {
            System.out.println("Pago exitoso con " + pago.getMetodo() + " por $" + pedido.getTotal());
            Factura.generarFactura(pedido, cliente);
            return true;
        } else {
            System.out.println("Pago rechazado. Monto insuficiente.");
            return false;
        }
    }
}
