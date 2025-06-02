package main.java.org.example.modelo;

public class EnEsperaState implements PedidoState {
    @Override public void avanzarEstado(Pedido pedido) {
        pedido.setEstado(new EnPreparacionState());
    }
    @Override public void notificar(Pedido pedido) {
        pedido.getNotifier().notifyAll(pedido, "Su pedido está en espera.");
    }
}
// src/main/java/org/example/modelo/EnEsperaState.java
@Override
public int calcularTiempoRestante(Pedido pedido, int pedidosTotales, boolean esDelivery, int tiempoRappi) {
    return pedidosTotales >= 10 ? 5 + ((pedidosTotales / 10) * 20) : 5;
}
