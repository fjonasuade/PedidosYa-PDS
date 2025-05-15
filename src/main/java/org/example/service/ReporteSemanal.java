package main.java.org.example.service;
import main.java.org.example.modelo.*;
import main.java.org.example.repository.PedidoRepository;
import java.time.*;
import java.util.*;
public class ReporteSemanal extends Reporte {
    public ReporteSemanal(PedidoRepository repo) { super(repo); }
    @Override public void generar() {
        LocalDate inicioSemana = LocalDate.now().minusDays(7);
        long count = repo.findAll().stream()
                .filter(p -> /* lógica fecha semana */ true)
                .count();
        System.out.println("Pedidos esta semana: " + count);
    }
}