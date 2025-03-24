package main.java.org.example.view;

import main.java.org.example.controller.PagoController;
import main.java.org.example.controller.PedidoController;
import main.java.org.example.modelo.*;

import java.util.Scanner;

public class ClienteInterface {
    private Scanner scanner = new Scanner(System.in);
    private PedidoController pedidoCtrl = new PedidoController();
    private PagoController pagoCtrl = new PagoController();

    public void seleccionarPlato(Menu menu, Pedido pedido) {
        System.out.println("Selecciona una categoría:");
        for (int i = 0; i < menu.getCategorias().size(); i++) {
            System.out.println((i + 1) + ". " + menu.getCategorias().get(i).getPlatos().get(0).getNombre()); // Just display the category names
        }
        int categoriaSeleccionada = scanner.nextInt();
        Categoria categoria = menu.getCategorias().get(categoriaSeleccionada - 1);

        System.out.println("Platos disponibles:");
        for (Plato plato : categoria.getPlatos()) {
            System.out.println(plato.getNombre() + " - $" + plato.getPrecio() + " | " + plato.getDescripcion());
        }

        System.out.println("Selecciona un plato:");
        int platoSeleccionado = scanner.nextInt();
        Plato plato = categoria.getPlatos().get(platoSeleccionado - 1);

        pedidoCtrl.agregarPlato(pedido, plato);
        System.out.println("Plato agregado: " + plato.getNombre());
    }

    public void realizarPago(Pedido pedido) {
        System.out.println("Método de pago: 1. Tarjeta de Crédito 2. Tarjeta de Débito 3. Efectivo 4. PayPal");
        int metodoSeleccionado = scanner.nextInt();
        String metodo = "";
        switch (metodoSeleccionado) {
            case 1: metodo = "Tarjeta de Crédito"; break;
            case 2: metodo = "Tarjeta de Débito"; break;
            case 3: metodo = "Efectivo"; break;
            case 4: metodo = "PayPal"; break;
        }
        System.out.println("Monto a pagar: $" + pedido.getTotal());
        System.out.println("Ingrese el monto del pago:");
        double monto = scanner.nextDouble();
        Pago pago = new Pago(metodo, monto);

        pagoCtrl.procesarPago(pedido, pago);
    }
}
