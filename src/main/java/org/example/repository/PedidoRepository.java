package main.java.org.example.repository;

import main.java.org.example.modelo.Pedido;
import java.util.List;

public interface PedidoRepository {
    void save(Pedido pedido);
    List<Pedido> findAll();
    List<Pedido> findByEstado(String estado);
}