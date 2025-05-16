package main.java.org.example.view;

import main.java.org.example.controller.PedidoController;
import main.java.org.example.controller.PagoController;
import main.java.org.example.controller.ReporteController;
import main.java.org.example.modelo.*;
import main.java.org.example.service.Reporte;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EmpleadoInterface {
    private Scanner scanner = new Scanner(System.in);
    private ReporteController reporteCtrl = new ReporteController();
    private PedidoController pedidoCtrl = PedidoController.getInstance();
    private PagoController pagoCtrl = PagoController.getInstance();
    private Map<String, ICupon> cupones = new HashMap<>();

    // Constructor para inicializar los cupones
    public EmpleadoInterface() {
        inicializarCupones();
    }

    private void inicializarCupones() {
        // Cupones de montos fijos
        cupones.put("DESC10", new DescuentoFijo("DESC10", 10.0));
        cupones.put("DESC20", new DescuentoFijo("DESC20", 20.0));
        cupones.put("DESC50", new DescuentoFijo("DESC50", 50.0));

        // Cupones de porcentaje
        cupones.put("10OFF", new DescuentoPorPorcentaje("10OFF", 10.0));
        cupones.put("20OFF", new DescuentoPorPorcentaje("20OFF", 20.0));
        cupones.put("50OFF", new DescuentoPorPorcentaje("50OFF", 50.0));
    }

    /**
     * Menú para gestionar cupones de descuento
     */
    public void gestionarCupones() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n===== GESTIÓN DE CUPONES =====");
            System.out.println("1. Listar cupones disponibles");
            System.out.println("2. Crear nuevo cupón");
            System.out.println("3. Modificar cupón existente");
            System.out.println("4. Eliminar cupón");
            System.out.println("5. Volver al menú principal");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1: listarCupones(); break;
                case 2: crearCupon(); break;
                case 3: modificarCupon(); break;
                case 4: eliminarCupon(); break;
                case 5: continuar = false; break;
                default: System.out.println("Opción inválida");
            }
        }
    }

    private void listarCupones() {
        System.out.println("\n--- CUPONES DISPONIBLES ---");

        if (cupones.isEmpty()) {
            System.out.println("No hay cupones disponibles.");
            return;
        }

        System.out.println("CÓDIGO | TIPO | VALOR | DESCRIPCIÓN");
        for (Map.Entry<String, ICupon> entry : cupones.entrySet()) {
            String codigo = entry.getKey();
            ICupon cupon = entry.getValue();
            String tipo = cupon instanceof DescuentoFijo ? "Fijo" : "Porcentaje";
            String valor = "";

            if (cupon instanceof DescuentoFijo) {
                valor = "$" + ((DescuentoFijo) cupon).getMontoFijo();
            } else if (cupon instanceof DescuentoPorPorcentaje) {
                valor = ((DescuentoPorPorcentaje) cupon).getPorcentaje() + "%";
            }

            System.out.printf("%s | %s | %s | %s\n",
                codigo, tipo, valor, cupon.getDescripcion());
        }
    }

    private void crearCupon() {
        System.out.println("\n--- CREAR NUEVO CUPÓN ---");
        System.out.print("Ingrese el código del cupón: ");
        String codigo = scanner.nextLine().toUpperCase();

        if (cupones.containsKey(codigo)) {
            System.out.println("Error: El código ya existe. Utilice otro código o modifique el existente.");
            return;
        }

        System.out.println("Seleccione el tipo de descuento:");
        System.out.println("1. Monto fijo");
        System.out.println("2. Porcentaje");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        ICupon nuevoCupon = null;

        if (tipo == 1) {
            System.out.print("Ingrese el monto del descuento: $");
            double monto = scanner.nextDouble();
            scanner.nextLine(); // Limpiar buffer
            nuevoCupon = new DescuentoFijo(codigo, monto);

        } else if (tipo == 2) {
            System.out.print("Ingrese el porcentaje de descuento: ");
            double porcentaje = scanner.nextDouble();
            scanner.nextLine(); // Limpiar buffer
            nuevoCupon = new DescuentoPorPorcentaje(codigo, porcentaje);

        } else {
            System.out.println("Opción inválida.");
            return;
        }

        cupones.put(codigo, nuevoCupon);
        System.out.println("Cupón creado con éxito: " + nuevoCupon.getDescripcion());
    }

    private void modificarCupon() {
        System.out.println("\n--- MODIFICAR CUPÓN ---");
        System.out.print("Ingrese el código del cupón a modificar: ");
        String codigo = scanner.nextLine().toUpperCase();

        if (!cupones.containsKey(codigo)) {
            System.out.println("Error: El cupón no existe.");
            return;
        }

        ICupon cuponActual = cupones.get(codigo);
        System.out.println("Cupón actual: " + cuponActual.getDescripcion());

        // Mantener el mismo tipo de cupón al modificar
        if (cuponActual instanceof DescuentoFijo) {
            System.out.print("Ingrese el nuevo monto de descuento: $");
            double nuevoMonto = scanner.nextDouble();
            scanner.nextLine(); // Limpiar buffer
            cupones.put(codigo, new DescuentoFijo(codigo, nuevoMonto));

        } else if (cuponActual instanceof DescuentoPorPorcentaje) {
            System.out.print("Ingrese el nuevo porcentaje de descuento: ");
            double nuevoPorcentaje = scanner.nextDouble();
            scanner.nextLine(); // Limpiar buffer
            cupones.put(codigo, new DescuentoPorPorcentaje(codigo, nuevoPorcentaje));
        }

        System.out.println("Cupón modificado con éxito: " + cupones.get(codigo).getDescripcion());
    }

    private void eliminarCupon() {
        System.out.println("\n--- ELIMINAR CUPÓN ---");
        System.out.print("Ingrese el código del cupón a eliminar: ");
        String codigo = scanner.nextLine().toUpperCase();

        if (!cupones.containsKey(codigo)) {
            System.out.println("Error: El cupón no existe.");
            return;
        }

        ICupon cuponEliminado = cupones.remove(codigo);
        System.out.println("Cupón eliminado: " + cuponEliminado.getDescripcion());
    }

    public void generarReporte() {
        System.out.println("Generar reporte: 1. Diario 2. Semanal 3. Mensual 4. Anual");
        int opcion = scanner.nextInt();
        Reporte reporte = null;

        switch (opcion) {
            case 1:
                reporte = reporteCtrl.crearReporte("diario");
                break;
            case 2:
                reporte = reporteCtrl.crearReporte("semanal");
                break;
            case 3:
                reporte = reporteCtrl.crearReporte("mensual");
                break;
            case 4:
                System.out.println("Reporte anual no implementado.");
                break;
            default:
                System.out.println("Opción no válida.");
        }

        if (reporte != null) {
            reporte.generar();
        }
    }

    public void modificarPlatos() {
        // Código para modificar platos
    }

    public void verificarEstadoPedido(Pedido pedido) {
        pedidoCtrl.mostrarEstado(pedido);
    }

    public void cambiarEstadoPedido(Pedido pedido) {
        System.out.println("¿Avanzar el estado del pedido? (s/n)");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            pedidoCtrl.avanzarEstadoPedido(pedido);
            System.out.println("Estado actualizado a: " + pedido.getEstado().getClass().getSimpleName());
        }
    }

    public void cobrarCliente(Pedido pedido) {
        System.out.println("Método de pago: 1. Tarjeta de Crédito 2. Tarjeta de Débito 3. MercadoPago 4. Efectivo");
        int metodoSeleccionado = scanner.nextInt();
        IPago pago = null;
        double monto = pedido.getTotal();

        System.out.println("Monto a pagar: $" + monto);

        switch (metodoSeleccionado) {
            case 1:
                pago = new PagoTarjetaCredito(monto);
                break;
            case 2:
                pago = new PagoTarjetaDebito(monto);
                break;
            case 3:
                pago = new PagoMercadoPago(monto);
                break;
            case 4:
                System.out.print("Ingrese monto en efectivo: $");
                double efectivo = scanner.nextDouble();
                pago = new PagoEfectivo(efectivo);
                break;
            default:
                System.out.println("Opción inválida");
        }

        if (pago != null && pagoCtrl.procesarPago(pedido, pago)) {
            Notificacion.enviarNotificacion(pedido.getCliente(), "Pago realizado con éxito. Su pedido está listo para ser entregado.");
            pedido.avanzar(); // Avanza el estado en lugar de cambiarlo directamente
        } else {
            System.out.println("Pago rechazado. Intente nuevamente.");
        }
    }

    public ICupon validarCupon(String codigo) {
        return cupones.get(codigo != null ? codigo.toUpperCase() : null);
    }
}