package main.java.org.example.modelo;

public class EmailNotifier implements Observer {
    @Override
    public void update(Pedido pedido, String mensaje) {
        System.out.printf("[EMAIL a %s]: %s\n",
                pedido.getCliente().getEmail(), mensaje);
    }
}