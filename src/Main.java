import main.java.org.example.controller.PedidoController;
import main.java.org.example.modelo.*;
import main.java.org.example.view.ClienteInterface;
import main.java.org.example.view.EmpleadoInterface;

public class Main {
    public static void main(String[] args) {
            // Crear objetos
            Menu menu = Menu.getInstancia();
            PedidoController pedidoCtrl = PedidoController.getInstance();
            Cliente cliente = new Cliente("Juan Pérez", "juan@example.com");

            // Crear interfaces
            ClienteInterface clienteInterface = new ClienteInterface();
            EmpleadoInterface empleadoInterface = new EmpleadoInterface();

            // Crear categorías y platos
            Categoria entradas = new Categoria("Entradas");
            entradas.agregarItem(new Plato("Sopa de Pollo", "Sopa con trozos de pollo", 5.99, false));
            Categoria platosPrincipales = new Categoria("Platos Principales");
            platosPrincipales.agregarItem(new Plato("Pizza", "Pizza de pepperoni", 12.99, false));
            Categoria postres = new Categoria("Postres");
            postres.agregarItem(new Plato("Tiramisú", "Postre italiano", 4.99, true));
            Categoria bebidas = new Categoria("Bebidas");
            bebidas.agregarItem(new Plato("Coca Cola", "Refresco", 1.50, false));

            menu.agregarCategoria(entradas);
            menu.agregarCategoria(platosPrincipales);
            menu.agregarCategoria(postres);
            menu.agregarCategoria(bebidas);

            // Crear pedido
            Pedido pedido = pedidoCtrl.crearPedido(cliente);

            // El cliente selecciona platos
            clienteInterface.seleccionarPlato(menu, pedido);
            clienteInterface.seleccionarPlato(menu, pedido);

            // Aplicar cupón si está disponible
            clienteInterface.aplicarCupon(pedido, empleadoInterface);

            // Realizar pago
            clienteInterface.realizarPago(pedido);

            // Mostrar estado del pedido
            clienteInterface.verEstadoPedido(pedido);
        }
}