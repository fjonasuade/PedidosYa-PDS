package main.java.org.example.modelo;

public class EnEsperaState implements PedidoState {
    @Override public void avanzarEstado(Pedido pedido) {
        pedido.setEstado(new EnPreparacionState());
    }
    @Override public void notificar(Pedido pedido) {
        pedido.getNotifier().notifyAll(pedido, "Su pedido está en espera.");
    }
}