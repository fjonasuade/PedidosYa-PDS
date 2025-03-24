package main.java.org.example.modelo;

public class Factura {
    public static void generarFactura(Pedido pedido) {
        System.out.println("Factura generada para: " + pedido.getCliente().getEmail());
        System.out.println("Número de Orden: " + pedido.getNumeroOrden());
        System.out.println("Total: $" + pedido.getTotal());
        System.out.println("Descuento aplicado: $" + pedido.getDescuento());
        System.out.println("Estado del pedido: " + pedido.getEstado());
    }
}
