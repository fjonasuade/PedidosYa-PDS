package main.java.org.example.modelo;

public class Notificacion {
    public static void enviarNotificacion(Cliente cliente, String mensaje) {
        System.out.println("Notificación enviada a " + cliente.getEmail() + ": " + mensaje);
    }
}