package Aproyecto;

public abstract class ItemVenta {
    private String id;
    private String nombre;
    private double precio;

    public ItemVenta(String id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "{\"ID\": \"" + id + "\", \"Nombre\": \"" + nombre + "\", \"Precio\": " + precio + "}";
    }
}
