package main.java.org.example.controller;
import main.java.org.example.modelo.*;
import main.java.org.example.repository.*;
public class PedidoController {
    private static final PedidoController INSTANCE = new PedidoController();
    private PedidoRepository repo = new InMemoryPedidoRepository();
    private PedidoController() {}
    public static PedidoController getInstance() { return INSTANCE; }

    public Pedido crearPedido(Cliente cliente) {
        Pedido p = new Pedido(cliente);
        repo.save(p);
        return p;
    }
    public void agregarPlato(Pedido p, MenuItem item) {
        if (item instanceof Plato) p.agregarPlato((Plato)item);
    }
    public void aplicarCupon(Pedido p, ICupon c) { p.aplicarDescuento(c); }
    public void avanzarEstadoPedido(Pedido p) { p.avanzar(); }
    public void mostrarEstado(Pedido p) { System.out.println(Factura.generar(p)); }
}
