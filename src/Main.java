import main.java.org.example.controller.PagoController;
import main.java.org.example.controller.PedidoController;
import main.java.org.example.modelo.Cliente;
import main.java.org.example.modelo.Pago;
import main.java.org.example.modelo.Pedido;
import main.java.org.example.modelo.Plato;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PedidoController pedidoCtrl = new PedidoController();
        PagoController pagoCtrl = new PagoController();
        Cliente cliente = new Cliente("Juan Pérez", "juan@example.com");

        Pedido pedido = pedidoCtrl.crearPedido();
        Plato plato1 = new Plato("Pizza", "Pizza de pepperoni", 12.99, false);
        pedidoCtrl.agregarPlato(pedido, plato1);

        System.out.println("Estado del pedido: " + pedido.getEstado());
        pedidoCtrl.aplicarDescuento(pedido, 10);
        pedidoCtrl.cambiarEstadoPedido(pedido, "En preparación", cliente);
        System.out.println("Nuevo estado: " + pedido.getEstado());

        Pago pago = new Pago("Tarjeta de Crédito", 20.00);
        pagoCtrl.procesarPago(pedido, pago, cliente);
    }
}