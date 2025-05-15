package main.java.org.example.service;
import main.java.org.example.repository.PedidoRepository;
public abstract class Reporte {
    protected PedidoRepository repo;
    public Reporte(PedidoRepository repo) { this.repo = repo; }
    public abstract void generar();
}