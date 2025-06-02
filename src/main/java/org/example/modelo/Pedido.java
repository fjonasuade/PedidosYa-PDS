package main.java.org.example.modelo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    public Object numeroOrden;
    private Cliente cliente;
    private PedidoState estado;
    private Notificador notificador;
    private double total;
    private List<Plato> platos = new ArrayList<>();

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.estado = new EnEsperaState(); // Estado inicial
        this.notificador = new Notificador();
        this.total = 0.0;
    }
    public void aplicarDescuento(ICupon cupon) {
        if (cupon != null) {
            double descuento = cupon.calcularDescuento(this.total);
            this.total -= descuento;
        }
    }
    private LocalDateTime programadoPara;
    public void setProgramadoPara(LocalDateTime fechaHora) { this.programadoPara = fechaHora; }
    public LocalDateTime getProgramadoPara() { return programadoPara; }
    public boolean isProgramado() { return programadoPara != null; }
    public boolean estaActivo() {
        return !isProgramado() || LocalDateTime.now().isAfter(programadoPara);
    }
    // src/main/java/org/example/modelo/Pedido.java
    public boolean cancelar() {
        if (estado instanceof EnEsperaState || estado instanceof EnPreparacionState) {
            double reembolso = getTotal() * 0.75;
            // Refund logic here
            setEstado(new CanceladoState());
            return true;
        }
        return false;
    }

    public void agregarPlato(Plato plato) {
        if (estado instanceof EnEsperaState) {
            platos.add(plato);
            total += plato.getPrecio();
        } else {
            throw new IllegalStateException("Solo se pueden agregar productos en estado 'en espera'");
        }
    }

    public List<Plato> getPlatos() {
        return new ArrayList<>(platos);
    }

    private Plataforma plataforma;
    public void setPlataforma(Plataforma plataforma) { this.plataforma = plataforma; }
    public Plataforma getPlataforma() { return plataforma; }

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

    // Nuevo método para calcular el tiempo restante
    public int calcularTiempoRestante(int pedidosTotales, boolean esDelivery, int tiempoRappi) {
        return estado.calcularTiempoRestante(this, pedidosTotales, esDelivery, tiempoRappi);
    }
}

