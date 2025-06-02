// PedidoState.java (State)
package main.java.org.example.modelo;
public interface PedidoState {
    void avanzarEstado(Pedido pedido);
    void notificar(Pedido pedido);
}
// src/main/java/org/example/modelo/PedidoState.java
package main.java.org.example.modelo;

public interface PedidoState {
    void avanzarEstado(Pedido pedido);
    void notificar(Pedido pedido);
    int calcularTiempoRestante(Pedido pedido, int pedidosTotales, boolean esDelivery, int tiempoRappi);
}