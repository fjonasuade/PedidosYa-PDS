package main.java.org.example.view;

import main.java.org.example.controller.PedidoController;
import main.java.org.example.modelo.*;
import java.util.List;
import java.util.Scanner;

public class ClienteInterface {
    private Scanner scanner = new Scanner(System.in);
    private PedidoController pedidoCtrl = PedidoController.getInstance();

    public void mostrarMenu(Menu menu) {
        System.out.println("\n*** MENÚ DEL RESTAURANTE ***");
        List<Categoria> categorias = menu.getCategorias();
        for (int i = 0; i < categorias.size(); i++) {
            Categoria cat = categorias.get(i);
            System.out.printf("%d) %s\n", i + 1, cat.getNombre());
            List<MenuItem> items = cat.getItems();
            for (int j = 0; j < items.size(); j++) {
                MenuItem item = items.get(j);
                if (item instanceof Plato) {
                    System.out.printf("   %d.%d) %s - $%.2f\n",
                        i + 1, j + 1, item.getNombre(), item.getPrecio());
                }
            }
        }
    }

    public void seleccionarPlato(Menu menu, Pedido pedido) {
        mostrarMenu(menu);
        System.out.print("Seleccione categoría (0 para finalizar): ");
        int catIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir salto de línea

        if (catIndex < 0) return;

        List<Categoria> categorias = menu.getCategorias();
        if (catIndex >= 0 && catIndex < categorias.size()) {
            Categoria cat = categorias.get(catIndex);
            List<MenuItem> items = cat.getItems();

            System.out.println("Platos disponibles en " + cat.getNombre() + ":");
            for (int i = 0; i < items.size(); i++) {
                MenuItem item = items.get(i);
                if (item instanceof Plato) {
                    System.out.printf("%d) %s - $%.2f - %s\n",
                        i + 1, item.getNombre(), item.getPrecio(), item.getDescripcion());
                }
            }

            System.out.print("Seleccione plato: ");
            int platoIndex = scanner.nextInt() - 1;
            scanner.nextLine(); // Consumir salto de línea

            if (platoIndex >= 0 && platoIndex < items.size() && items.get(platoIndex) instanceof Plato) {
                pedidoCtrl.agregarPlato(pedido, items.get(platoIndex));
                System.out.println(items.get(platoIndex).getNombre() + " añadido al pedido.");
            }
        }
    }

    public void aplicarCupon(Pedido pedido, EmpleadoInterface empleadoInterface) {
        System.out.println("¿Tiene un cupón de descuento? (S/N)");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("S")) {
            System.out.print("Ingrese el código del cupón: ");
            String codigo = scanner.nextLine().toUpperCase();

            ICupon cupon = empleadoInterface.validarCupon(codigo);
            if (cupon != null) {
                pedidoCtrl.aplicarCupon(pedido, cupon);
                System.out.println("Cupón aplicado: " + cupon.getDescripcion());
            } else {
                System.out.println("El cupón no es válido.");
            }
        }
    }

    public void realizarPago(Pedido pedido) {
        System.out.println("\n*** SELECCIONE MÉTODO DE PAGO ***");
        System.out.println("1) Efectivo");
        System.out.println("2) Tarjeta de Débito");
        System.out.println("3) Tarjeta de Crédito");
        System.out.println("4) MercadoPago");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        IPago metodoPago = null;
        switch(opcion) {
            case 1:
                metodoPago = new PagoEfectivo(pedido.getTotal());
                break;
            case 2:
                metodoPago = new PagoTarjetaDebito(pedido.getTotal());
                break;
            case 3:
                metodoPago = new PagoTarjetaCredito(pedido.getTotal());
                break;
            case 4:
                metodoPago = new PagoMercadoPago(pedido.getTotal());
                break;
        }

        if (metodoPago != null && metodoPago.procesarPago(pedido.getTotal())) {
            System.out.println("Pago procesado correctamente con " + metodoPago.getMetodo());
            pedido.avanzar(); // Avanzar estado del pedido
            pedido.notificar("Su pedido ha sido confirmado");
        } else {
            System.out.println("Error al procesar el pago");
        }
    }

    public void verEstadoPedido(Pedido pedido) {
        System.out.println("\n*** ESTADO DEL PEDIDO ***");
        System.out.println("Cliente: " + pedido.getCliente().getNombre());
        System.out.println("Total: $" + pedido.getTotal());
        System.out.println("Estado: " + pedido.getEstado().getClass().getSimpleName());
    }
}