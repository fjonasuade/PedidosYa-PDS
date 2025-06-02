package main.java.org.example.modelo;

public class EnPreparacionState implements PedidoState {
    @Override public void avanzarEstado(Pedido pedido) {
        pedido.setEstado(new ListoParaEntregarState());
    }
    @Override public void notificar(Pedido pedido) {
        pedido.getNotifier().notifyAll(pedido, "Su pedido está en preparación.");
    }
}
// src/main/java/org/example/modelo/EnPreparacionState.java
@Override
public int calcularTiempoRestante(Pedido pedido, int pedidosTotales, boolean esDelivery, int tiempoRappi) {
    return pedido.getPlatos().stream().mapToInt(Plato::getTiempoPreparacion).sum();
}