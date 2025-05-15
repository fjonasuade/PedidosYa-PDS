
// ReporteController.java (Factory Method)
package main.java.org.example.controller;
import main.java.org.example.service.*;
import main.java.org.example.repository.*;
public class ReporteController {
    private PedidoRepository repo = new InMemoryPedidoRepository();
    public ReporteController() {}
    public Reporte crearReporte(String tipo) {
        switch(tipo.toLowerCase()) {
            case "diario": return new ReporteDiario(repo);
            case "semanal": return new ReporteSemanal(repo);
            case "mensual": return new ReporteMensual(repo);
            default: throw new IllegalArgumentException("Tipo no soportado");
        }
    }
}