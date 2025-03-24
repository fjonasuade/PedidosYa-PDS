import main.java.org.example.controller.PagoController;
import main.java.org.example.controller.PedidoController;
import main.java.org.example.modelo.*;
import main.java.org.example.view.ClienteInterface;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
            // Crear objetos
            Menu menu = new Menu();
            PedidoController pedidoCtrl = new PedidoController();
            Cliente cliente = new Cliente("Juan Pérez", "juan@example.com");

            // Crear categorías y platos
            Categoria entradas = new Categoria("Entradas");
            entradas.agregarPlato(new Plato("Sopa de Pollo", "Sopa con trozos de pollo", 5.99, false));
            Categoria platosPrincipales = new Categoria("Platos Principales");
            platosPrincipales.agregarPlato(new Plato("Pizza", "Pizza de pepperoni", 12.99, false));
            Categoria postres = new Categoria("Postres");
            postres.agregarPlato(new Plato("Tiramisú", "Postre italiano", 4.99, true));
            Categoria bebidas = new Categoria("Bebidas");
            bebidas.agregarPlato(new Plato("Coca Cola", "Refresco", 1.50, false));

            menu.agregarCategoria(entradas);
            menu.agregarCategoria(platosPrincipales);
            menu.agregarCategoria(postres);
            menu.agregarCategoria(bebidas);

            // Interfaz de cliente
            ClienteInterface clienteInterface = new ClienteInterface();
            Pedido pedido = pedidoCtrl.crearPedido(cliente);

            // El cliente selecciona platos
            clienteInterface.seleccionarPlato(menu, pedido);
            clienteInterface.seleccionarPlato(menu, pedido);

            // Realizar pago
            clienteInterface.realizarPago(pedido);

            // Mostrar estado del pedido
            pedidoCtrl.mostrarEstadoPedido(pedido);
        }
}