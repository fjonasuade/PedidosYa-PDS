// PedidoState.java (State)
package main.java.org.example.modelo;
public interface PedidoState {
    void avanzarEstado(Pedido pedido);
    void notificar(Pedido pedido);
}