// PagoController.java (Singleton)
package main.java.org.example.controller;
import main.java.org.example.modelo.*;
public class PagoController {
    private static final PagoController INSTANCE = new PagoController();
    private PagoController() {}  // Constructor privado para Singleton
    public static PagoController getInstance() { return INSTANCE; }

    public boolean procesarPago(Pedido p, IPago pago) {
        boolean ok = pago.procesarPago(p.getTotal());  // Cambio de pagar a procesarPago
        if (ok) p.avanzar();
        return ok;
    }
}