package main.java.org.example.modelo;
import java.util.*;

public class Notificador {
    private List<Observer> observers = new ArrayList<>();
    public void addObserver(Observer o) { observers.add(o); }
    public void removeObserver(Observer o) { observers.remove(o); }
    public void notifyAll(Pedido p, String mensaje) {
        for (Observer o: observers) o.update(p, mensaje);
    }
}