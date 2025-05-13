// ListoParaEntregarState.java
package main.java.org.example.modelo;
public class ListoParaEntregarState implements PedidoState {
    @Override public void avanzarEstado(Pedido pedido) {
        pedido.setEstado(new EntregadoState());
    }
    @Override public void notificar(Pedido pedido) {
        pedido.getNotifier().notifyAll(pedido, "Su pedido está listo para entregar.");
    }
}