// PagoController.java (Singleton)
package main.java.org.example.controller;
import main.java.org.example.modelo.*;
public class PagoController {
    private static final PagoController INSTANCE = new PagoController();
    private PagoController() {}
    public static PagoController getInstance() { return INSTANCE; }

    public boolean procesarPago(Pedido p, IPago pago) {
        boolean ok = pago.pagar(p.getTotal());
        if (ok) p.avanzar();
        return ok;
    }
}