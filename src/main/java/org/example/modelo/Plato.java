package main.java.org.example.modelo;

public class Plato {
    private String nombre;
    private String descripcion;
    private double precio;
    private boolean contieneAlergenos;

    public Plato(String nombre, String descripcion, double precio, boolean contieneAlergenos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.contieneAlergenos = contieneAlergenos;
    }

    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public boolean contieneAlergenos() { return contieneAlergenos; }
}
