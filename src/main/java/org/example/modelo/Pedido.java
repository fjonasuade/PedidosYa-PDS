package main.java.org.example.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contadorPedidos = 1;
    private int numeroOrden;
    private List<Plato> platos = new ArrayList<>();
    private double total;
    private String estado;
    private double descuento;
    private Cliente cliente;

    public Pedido() {
        this.numeroOrden = contadorPedidos++;
        this.estado = "En espera";
        this.descuento = 0;
        this.cliente = cliente;
    }

    public void agregarPlato(Plato plato) {
        platos.add(plato);
        total += plato.getPrecio();
    }

    public void aplicarDescuento(double porcentaje) {
        this.descuento = total * (porcentaje / 100);
        total -= descuento;
    }

    public void cambiarEstado(String nuevoEstado) { this.estado = nuevoEstado; }
    public String getEstado() { return estado; }
    public double getTotal() { return total; }
    public int getNumeroOrden() { return numeroOrden; }
    public double getDescuento() { return descuento; }
    public Cliente getCliente() { return cliente; }
}
