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
            System.out.println((i + 1) + ". " + menu.getCategorias().get(i).getPlatos().get(0).getNombre());
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
        System.out.println("Método de pago: 1. Tarjeta de Crédito 2. Tarjeta de Débito 3. MercadoPago 4. PayPal 5. Transferencia Bancaria");
        int metodoSeleccionado = scanner.nextInt();
        IPago pago = null;
        System.out.println("Monto a pagar: $" + pedido.getTotal());
        System.out.println("Ingrese el monto del pago:");
        double monto = scanner.nextDouble();

        switch (metodoSeleccionado) {
            case 1: pago = new PagoTarjetaCredito(monto); break;
            case 2: pago = new PagoTarjetaDebito(monto); break;
            case 3: pago = new PagoMercadoPago(monto); break;
        }

        if (pago != null) {
            pagoCtrl.procesarPago(pedido, pago);
        } else {
            System.out.println("Método de pago no válido.");
        }
    }
}