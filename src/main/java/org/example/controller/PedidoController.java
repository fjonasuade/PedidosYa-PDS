package main.java.org.example.controller;

import main.java.org.example.modelo.Cliente;
import main.java.org.example.modelo.Notificacion;
import main.java.org.example.modelo.Pedido;
import main.java.org.example.modelo.Plato;

public class PedidoController {
    public Pedido crearPedido() { return new Pedido(); }
    public void agregarPlato(Pedido pedido, Plato plato) { pedido.agregarPlato(plato); }
    public void aplicarDescuento(Pedido pedido, double porcentaje) { pedido.aplicarDescuento(porcentaje); }
    public void cambiarEstadoPedido(Pedido pedido, String estado, Cliente cliente) {
        pedido.cambiarEstado(estado);
        Notificacion.enviarNotificacion(cliente, "Su pedido " + pedido.getNumeroOrden() + " ahora está: " + estado);
    }
}
