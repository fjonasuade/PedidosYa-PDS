package main.java.org.example.controller;

import main.java.org.example.modelo.Cliente;
import main.java.org.example.modelo.Notificacion;
import main.java.org.example.modelo.Pedido;
import main.java.org.example.modelo.Plato;

public class PedidoController {
    public Pedido crearPedido(Cliente cliente) { return new Pedido(cliente); }

    public void agregarPlato(Pedido pedido, Plato plato) {
        pedido.agregarPlato(plato);
    }

    public void aplicarDescuento(Pedido pedido, double porcentaje) {
        pedido.aplicarDescuento(porcentaje);
    }

    public void cambiarEstadoPedido(Pedido pedido, String estado) {
        pedido.cambiarEstado(estado);
        Notificacion.enviarNotificacion(pedido.getCliente(), "Su pedido " + pedido.getNumeroOrden() + " ahora está: " + estado);
    }

    public void mostrarEstadoPedido(Pedido pedido) {
        System.out.println("Estado actual del pedido " + pedido.getNumeroOrden() + ": " + pedido.getEstado());
    }
}
