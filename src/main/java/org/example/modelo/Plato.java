package main.java.org.example.modelo;

public class Plato implements MenuItem {
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

    @Override
    public String getNombre() { return nombre; }

    @Override
    public String getDescripcion() { return descripcion; }

    @Override
    public double getPrecio() { return precio; }

    public boolean contieneAlergenos() { return contieneAlergenos; }

    @Override
    public void print() {
        System.out.printf("    • %s – $%.2f (%s)%s\n",
            nombre,
            precio,
            descripcion,
            contieneAlergenos ? " [¡Alérgenos!]" : ""
        );
    }
}