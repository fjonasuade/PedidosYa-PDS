package main.java.org.example.view;
import main.java.org.example.modelo.*;
import main.java.org.example.controller.*;
import java.util.*;

public class ClienteInterface {
    private Scanner scanner = new Scanner(System.in);
    private PedidoController pedidoCtrl = PedidoController.getInstance();
    private PagoController pagoCtrl = PagoController.getInstance();

    public void iniciar(Menu menu, Cliente cliente) {
        Pedido pedido = pedidoCtrl.crearPedido(cliente);
        menu.imprimirMenu();

        System.out.println("Elija categoría:");
        List<MenuItem> categorias = menu.getRaiz();
        for (int i=0; i<categorias.size(); i++) {
            System.out.printf("%d) %s\n", i+1, categorias.get(i).getNombre());
        }
        int c = scanner.nextInt()-1;
        Categoria cat = (Categoria) categorias.get(c);

        System.out.println("Elija plato:");
        List<MenuItem> items = cat.getItems();
        for (int i=0; i<items.size(); i++) {
            System.out.printf("%d) %s\n", i+1, items.get(i).getNombre());
        }
        int p = scanner.nextInt()-1;
        pedidoCtrl.agregarPlato(pedido, items.get(p));

        System.out.println("Total: $" + pedido.getTotal());
        // pagos y cupones…
    }
}