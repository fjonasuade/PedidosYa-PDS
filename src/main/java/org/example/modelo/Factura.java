// Factura.java (simple string formatter)
package main.java.org.example.modelo;
public class Factura {
    public static String generar(Pedido pedido) {
        return String.format("Factura #%s - Cliente: %s - Total: $%.2f",
                pedido.numeroOrden, pedido.getCliente().getNombre(), pedido.getTotal());
    }
}