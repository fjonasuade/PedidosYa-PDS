package main.java.org.example.service;
import main.java.org.example.modelo.*;
import main.java.org.example.repository.PedidoRepository;
import java.time.*;
import java.util.*;
public class ReporteMensual extends Reporte {
    public ReporteMensual(PedidoRepository repo) { super(repo); }
    @Override public void generar() {
        LocalDate inicioMes = LocalDate.now().withDayOfMonth(1);
        long count = repo.findAll().stream()
                .filter(p -> /* lógica fecha mes */ true)
                .count();
        System.out.println("Pedidos este mes: " + count);
    }
}