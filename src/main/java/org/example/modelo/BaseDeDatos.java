package main.java.org.example.modelo;
import java.util.*;
public class BaseDeDatos {
    private static final BaseDeDatos INSTANCE = new BaseDeDatos();
    private Map<String, Plato> platos = new HashMap<>();
    private List<Pedido> pedidos = new ArrayList<>();
    private BaseDeDatos() {}
    public static BaseDeDatos getInstance() { return INSTANCE; }

    public void agregarPlato(Plato p) { platos.put(p.getNombre(), p); }
    public void eliminarPlato(String nombre) { platos.remove(nombre); }
    public Plato buscarPlato(String nombre) { return platos.get(nombre); }

    public void guardarPedido(Pedido pedido) { pedidos.add(pedido); }
    public List<Pedido> obtenerPedidos() { return Collections.unmodifiableList(pedidos); }
}