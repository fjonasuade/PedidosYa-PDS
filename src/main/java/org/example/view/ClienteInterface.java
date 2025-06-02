package main.java.org.example.view;

import main.java.org.example.controller.PagoController;
import main.java.org.example.controller.PedidoController;
import main.java.org.example.modelo.*;

import java.util.List;
import java.util.Scanner;

public class ClienteInterface {
    private Scanner scanner = new Scanner(System.in);
    private PedidoController pedidoCtrl = PedidoController.getInstance(); // Usando Singleton
    private PagoController pagoCtrl = PagoController.getInstance(); // Usando Singleton

    public void mostrarMenu(Menu menu) {
        System.out.println("\n===== MENÚ DISPONIBLE =====");
        for (Categoria categoria : menu.getCategorias()) {
            categoria.print(); // Usa el método polimórfico print() de la interfaz MenuItem
            System.out.println(); // Separador entre categorías
        }
    }

    public void seleccionarPlato(Menu menu, Pedido pedido) {
        System.out.println("\n==== SELECCIÓN DE PLATO ====");

        // Mostrar categorías disponibles
        List<Categoria> categorias = menu.getCategorias();
        System.out.println("Categorías disponibles:");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println((i + 1) + ". " + categorias.get(i).getNombre());
        }

        System.out.print("Selecciona una categoría (1-" + categorias.size() + "): ");
        int categoriaSeleccionada = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        if (categoriaSeleccionada < 1 || categoriaSeleccionada > categorias.size()) {
            System.out.println("Selección inválida.");
            return;
        }

        Categoria categoria = categorias.get(categoriaSeleccionada - 1);

        // Mostrar ítems de la categoría seleccionada
        List<MenuItem> items = categoria.getItems();
        System.out.println("\nPlatos disponibles en " + categoria.getNombre() + ":");

        int index = 1;
        for (MenuItem item : items) {
            if (item instanceof Plato) {
                Plato plato = (Plato) item;
                System.out.printf("%d. %s - $%.2f | %s%s\n",
                    index++,
                    plato.getNombre(),
                    plato.getPrecio(),
                    plato.getDescripcion(),
                    plato.contieneAlergenos() ? " [¡Contiene alérgenos!]" : ""
                );
            }
        }

        if (index == 1) {
            System.out.println("No hay platos disponibles en esta categoría.");
            return;
        }

        System.out.print("Selecciona un plato (1-" + (index-1) + "): ");
        int platoIndex = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        if (platoIndex < 1 || platoIndex >= index) {
            System.out.println("Selección inválida.");
            return;
        }

        // Obtener el plato seleccionado
        int contador = 1;
        for (MenuItem item : items) {
            if (item instanceof Plato) {
                if (contador == platoIndex) {
                    Plato platoSeleccionado = (Plato) item;
                    pedidoCtrl.agregarPlato(pedido, platoSeleccionado);
                    System.out.println("¡Plato agregado: " + platoSeleccionado.getNombre() + "!");
                    break;
                }
                contador++;
            }
        }
    }

    public void aplicarCupon(Pedido pedido, EmpleadoInterface empleadoInterface) {
        System.out.println("\n==== APLICAR CUPÓN ====");
        System.out.print("¿Desea aplicar un cupón de descuento? (s/n): ");
        String respuesta = scanner.next();
        scanner.nextLine(); // Limpiar buffer

        if (respuesta.equalsIgnoreCase("s")) {
            System.out.print("Ingrese el código del cupón: ");
            String codigoCupon = scanner.next();
            scanner.nextLine(); // Limpiar buffer

            ICupon cupon = empleadoInterface.validarCupon(codigoCupon);
            if (cupon != null) {
                double totalAntes = pedido.getTotal();
                pedidoCtrl.aplicarCupon(pedido, cupon);

                System.out.println("Cupón aplicado: " + cupon.getDescripcion());
                System.out.printf("Total antes: $%.2f - Total con descuento: $%.2f\n",
                    totalAntes, pedido.getTotal());
            } else {
                System.out.println("El cupón ingresado no es válido o ha expirado.");
            }
        }
    }

    public void realizarPago(Pedido pedido) {
        System.out.println("\n==== REALIZAR PAGO ====");
        System.out.println("Monto a pagar: $" + pedido.getTotal());
        System.out.println("Métodos de pago disponibles:");
        System.out.println("1. Tarjeta de Crédito");
        System.out.println("2. Tarjeta de Débito");
        System.out.println("3. MercadoPago");
        System.out.println("4. Efectivo");

        System.out.print("Seleccione método de pago (1-4): ");
        int metodoSeleccionado = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        IPago pago = null;
        double monto = pedido.getTotal();

        switch (metodoSeleccionado) {
            case 1: pago = new PagoTarjetaCredito(monto); break;
            case 2: pago = new PagoTarjetaDebito(monto); break;
            case 3: pago = new PagoMercadoPago(monto); break;
            case 4:
                System.out.print("Ingrese el monto en efectivo: $");
                double montoEfectivo = scanner.nextDouble();
                scanner.nextLine(); // Limpiar buffer
                pago = new PagoEfectivo(montoEfectivo);
                break;
            default:
                System.out.println("Opción inválida.");
                return;
        }

        if (pago != null) {
            if (pagoCtrl.procesarPago(pedido, pago)) {
                System.out.println("¡Pago procesado correctamente con " + pago.getMetodo() + "!");
                Notificacion.enviarNotificacion(pedido.getCliente(),
                    "Su pago ha sido procesado correctamente. Su pedido está en preparación.");
            } else {
                System.out.println("Error al procesar el pago. Por favor, intente nuevamente.");
            }
        }
    }

    public void verEstadoPedido(Pedido pedido) {
        System.out.println("\n==== ESTADO DEL PEDIDO ====");
        System.out.println("Cliente: " + pedido.getCliente().getNombre());
        System.out.println("Estado actual: " + pedido.getEstado().getClass().getSimpleName());
        System.out.println("Total: $" + pedido.getTotal());

        // Mostrar factura
        System.out.println("\nFactura generada:");
        System.out.println(Factura.generar(pedido));
    }

    public void programarPedido(){

    }
}