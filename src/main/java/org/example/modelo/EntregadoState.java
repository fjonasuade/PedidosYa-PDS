package main.java.org.example.modelo;
public class EntregadoState implements PedidoState {
    @Override public void avanzarEstado(Pedido pedido) {
        // No avanza más
    }
    @Override public void notificar(Pedido pedido) {
        pedido.getNotifier().notifyAll(pedido, "Pedido entregado. ¡Gracias!");
    }
}