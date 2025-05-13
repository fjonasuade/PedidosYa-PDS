package main.java.org.example.modelo;

public class EnPreparacionState implements PedidoState {
    @Override public void avanzarEstado(Pedido pedido) {
        pedido.setEstado(new ListoParaEntregarState());
    }
    @Override public void notificar(Pedido pedido) {
        pedido.getNotifier().notifyAll(pedido, "Su pedido está en preparación.");
    }
}