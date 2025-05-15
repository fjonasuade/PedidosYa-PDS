package main.java.org.example.repository;

import main.java.org.example.modelo.Pedido;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryPedidoRepository implements PedidoRepository {
    private List<Pedido> pedidos = new ArrayList<>();

    @Override
    public void save(Pedido pedido) {
        pedidos.add(pedido);
    }

    @Override
    public List<Pedido> findAll() {
        return new ArrayList<>(pedidos);
    }

    @Override
    public List<Pedido> findByEstado(String estado) {
        return pedidos.stream()
                .filter(p -> p.getEstado().getClass().getSimpleName().equals(estado))
                .collect(Collectors.toList());
    }
}