package main.java.org.example.view;

import main.java.org.example.controller.PedidoController;
import main.java.org.example.controller.PagoController;
import main.java.org.example.controller.ReporteController;
import main.java.org.example.modelo.*;

import java.util.Scanner;

public class EmpleadoInterface {
    private Scanner scanner = new Scanner(System.in);
    private ReporteController reporteCtrl = new ReporteController();
    private PedidoController pedidoCtrl = new PedidoController();
    private PagoController pagoCtrl = new PagoController();

    public void generarReporte() {
        System.out.println("Generar reporte: 1. Diario 2. Semanal 3. Mensual 4. Anual");
        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1: reporteCtrl.generarReporteDiario(); break;
            case 2: reporteCtrl.generarReporteSemanal(); break;
            case 3: reporteCtrl.generarReporteMensual(); break;
            case 4: reporteCtrl.generarReporteAnual(); break;
            default: System.out.println("Opción no válida.");
        }
    }

    public void modificarPlatos() {
        System.out.println("Modificar platos: 1. Agregar 2. Eliminar");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        switch (opcion) {
            case 1:
                System.out.println("Ingrese nombre del plato:");
                String nombre = scanner.nextLine();
                System.out.println("Ingrese descripción del plato:");
                String descripcion = scanner.nextLine();
                System.out.println("Ingrese precio del plato:");
                double precio = scanner.nextDouble();
                System.out.println("¿Contiene alérgenos? (true/false):");
                boolean contieneAlergenos = scanner.nextBoolean();
                Plato nuevoPlato = new Plato(nombre, descripcion, precio, contieneAlergenos);
                BaseDeDatos.agregarPlato(nuevoPlato);
                System.out.println("Plato agregado: " + nombre);
                break;
            case 2:
                System.out.println("Ingrese nombre del plato a eliminar:");
                String nombreEliminar = scanner.nextLine();
                BaseDeDatos.eliminarPlato(nombreEliminar);
                System.out.println("Plato eliminado: " + nombreEliminar);
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    public void verificarEstadoPedido(Pedido pedido) {
        pedidoCtrl.mostrarEstadoPedido(pedido);
    }

    public void cambiarEstadoPedido(Pedido pedido) {
        System.out.println("Ingrese el nuevo estado del pedido:");
        String nuevoEstado = scanner.nextLine();
        pedidoCtrl.cambiarEstadoPedido(pedido, nuevoEstado);
    }

    public void cobrarCliente(Pedido pedido) {
        System.out.println("Método de pago: 1. Tarjeta de Crédito 2. Tarjeta de Débito 3. MercadoPago 4. Efectivo");
        int metodoSeleccionado = scanner.nextInt();
        IPago pago = null;
        System.out.println("Monto a pagar: $" + pedido.getTotal());

        switch (metodoSeleccionado) {
            case 1:
                pago = new PagoTarjetaCredito(pedido.getTotal());
                break;
            case 2:
                pago = new PagoTarjetaDebito(pedido.getTotal());
                break;
            case 3:
                pago = new PagoMercadoPago(pedido.getTotal());
                break;
            case 4:
                System.out.println("Ingrese el monto con el que abona el cliente:");
                double montoAbonado = scanner.nextDouble();
                pago = new PagoEfectivo(montoAbonado);
                double vuelto = montoAbonado - pedido.getTotal();
                System.out.println("Vuelto a entregar: $" + vuelto);
                break;
            default:
                System.out.println("Método de pago no válido.");
                return;
        }

        if (pago != null && pagoCtrl.procesarPago(pedido, pago)) {
            Notificacion.enviarNotificacion(pedido.getCliente(), "Pago realizado con éxito. Su pedido está listo para ser entregado.");
            pedidoCtrl.cambiarEstadoPedido(pedido, "Entregado");
        } else {
            System.out.println("Pago rechazado. Intente nuevamente.");
        }
    }
}