package main.java.org.example.service;
import main.java.org.example.modelo.*;
import main.java.org.example.repository.PedidoRepository;
import java.time.*;
import java.util.*;
public class ReporteDiario extends Reporte {
    public ReporteDiario(PedidoRepository repo) { super(repo); }
    @Override public void generar() {
        LocalDate hoy = LocalDate.now();
        long count = repo.findAll().stream()
                .filter(p -> /* lógica fecha */ true)
                .count();
        System.out.println("Pedidos hoy: " + count);
    }
}