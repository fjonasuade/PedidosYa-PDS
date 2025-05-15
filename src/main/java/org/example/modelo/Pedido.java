package main.java.org.example.modelo;

public class Pedido {
    private Cliente cliente;
    private PedidoState estado;
    private Notificador notificador;
    private double total;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.estado = new EnEsperaState(); // Estado inicial
        this.notificador = new Notificador();
        this.total = 0.0;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public PedidoState getEstado() {
        return estado;
    }

    public void setEstado(PedidoState estado) {
        this.estado = estado;
    }

    public Notificador getNotifier() {
        return notificador;
    }

    public void avanzar() {
        estado.avanzarEstado(this); // Delegar al estado actual
    }

    public void notificar(String mensaje) {
        estado.notificar(this); // Delegar al estado actual
    }

    public void agregarPlato(Plato plato) {
        total += plato.getPrecio();
    }
}