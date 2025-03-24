package main.java.org.example.modelo;

public class Factura {
    public static void generarFactura(Pedido pedido, Cliente cliente) {
        System.out.println("Factura generada para: " + cliente.getEmail());
        System.out.println("Número de Orden: " + pedido.getNumeroOrden());
        System.out.println("Total: $" + pedido.getTotal());
        System.out.println("Descuento aplicado: $" + pedido.getDescuento());
        System.out.println("Estado del pedido: " + pedido.getEstado());
    }
}
