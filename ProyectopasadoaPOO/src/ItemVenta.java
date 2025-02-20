package ProyectopasadoaPOO;

public abstract class ItemVenta {
    private String codigo;
    private String nombre;
    private double precio;

    public ItemVenta(String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters y Setters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }

    @Override
    public String toString() {
        return "Código: " + codigo + ", Nombre: " + nombre + ", Precio: " + precio;
    }
}