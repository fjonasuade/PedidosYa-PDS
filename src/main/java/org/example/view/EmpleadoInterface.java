package main.java.org.example.view;

import main.java.org.example.controller.PedidoController;
import main.java.org.example.modelo.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class EmpleadoInterface {
    private Scanner scanner = new Scanner(System.in);
    private PedidoController pedidoCtrl = PedidoController.getInstance();
    private Map<String, ICupon> cupones = new HashMap<>();

    public void mostrarPedidos(List<Pedido> pedidos) {
        System.out.println("\n*** PEDIDOS ACTIVOS ***");
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedido = pedidos.get(i);
            System.out.printf("%d) Cliente: %s - Total: $%.2f - Estado: %s\n",
                i + 1,
                pedido.getCliente().getNombre(),
                pedido.getTotal(),
                pedido.getEstado().getClass().getSimpleName());
        }
    }

    public void actualizarEstadoPedido(Pedido pedido) {
        System.out.println("Actualizando estado de pedido para " + pedido.getCliente().getNombre());
        pedido.avanzar();
        pedido.notificar("Estado actualizado");
        System.out.println("Estado actualizado a: " + pedido.getEstado().getClass().getSimpleName());
    }

    public void gestionarMenu(Menu menu) {
        System.out.println("\n*** GESTIONAR MENÚ ***");
        System.out.println("1) Ver menú");
        System.out.println("2) Agregar categoría");
        System.out.println("3) Agregar plato a categoría");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        switch(opcion) {
            case 1:
                menu.imprimirMenu();
                break;
            case 2:
                System.out.print("Nombre de la nueva categoría: ");
                String nombreCategoria = scanner.nextLine();
                menu.agregarCategoria(new Categoria(nombreCategoria));
                System.out.println("Categoría agregada con éxito");
                break;
            case 3:
                agregarPlatoACategoria(menu);
                break;
        }
    }

    private void agregarPlatoACategoria(Menu menu) {
        List<Categoria> categorias = menu.getCategorias();
        System.out.println("Seleccione una categoría:");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.printf("%d) %s\n", i + 1, categorias.get(i).getNombre());
        }

        int categoriaSeleccionada = scanner.nextInt() - 1;
        scanner.nextLine(); // Consumir salto de línea

        if (categoriaSeleccionada >= 0 && categoriaSeleccionada < categorias.size()) {
            Categoria categoria = categorias.get(categoriaSeleccionada);

            System.out.print("Nombre del plato: ");
            String nombre = scanner.nextLine();
            System.out.print("Descripción: ");
            String descripcion = scanner.nextLine();
            System.out.print("Precio: ");
            double precio = scanner.nextDouble();
            System.out.print("¿Contiene alérgenos? (true/false): ");
            boolean alergenos = scanner.nextBoolean();

            Plato nuevoPlato = new Plato(nombre, descripcion, precio, alergenos);
            categoria.agregarItem(nuevoPlato);
            System.out.println("Plato agregado con éxito");
        }
    }

    public void gestionarCupones() {
        System.out.println("\n*** GESTIONAR CUPONES ***");
        System.out.println("1) Ver cupones");
        System.out.println("2) Crear cupón de descuento fijo");
        System.out.println("3) Crear cupón de descuento porcentual");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        switch(opcion) {
            case 1:
                mostrarCupones();
                break;
            case 2:
                crearCuponFijo();
                break;
            case 3:
                crearCuponPorcentual();
                break;
        }
    }

    private void mostrarCupones() {
        System.out.println("\nCupones disponibles:");
        for (Map.Entry<String, ICupon> entrada : cupones.entrySet()) {
            System.out.println("Código: " + entrada.getKey() + " - " + entrada.getValue().getDescripcion());
        }
    }

    private void crearCuponFijo() {
        System.out.print("Código del cupón (letras mayúsculas): ");
        String codigo = scanner.nextLine().toUpperCase();
        System.out.print("Monto de descuento: $");
        double monto = scanner.nextDouble();

        cupones.put(codigo, new DescuentoFijo(monto));
        System.out.println("Cupón de descuento fijo creado: " + codigo);
    }

    private void crearCuponPorcentual() {
        System.out.print("Código del cupón (letras mayúsculas): ");
        String codigo = scanner.nextLine().toUpperCase();
        System.out.print("Porcentaje de descuento: ");
        double porcentaje = scanner.nextDouble();

        cupones.put(codigo, new DescuentoPorPorcentaje(porcentaje));
        System.out.println("Cupón de descuento porcentual creado: " + codigo);
    }

    public ICupon validarCupon(String codigo) {
        return cupones.get(codigo.toUpperCase());
    }
}