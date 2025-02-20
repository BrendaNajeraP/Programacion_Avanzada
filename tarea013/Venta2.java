package tarea013;

import java.util.List;

public class Venta2 {
    private String idTicket;
    private String fecha;
    private List<Producto2> productos;

    public Venta2(String idTicket, String fecha, List<Producto2> productos) {
        this.idTicket = idTicket;
        this.fecha = fecha;
        this.productos = productos;
    }

    // Getters y Setters
    public String getIdTicket() { return idTicket; }
    public String getFecha() { return fecha; }
    public List<Producto2> getProductos() { return productos; }

    @Override
    public String toString() {
        return "Ticket: " + idTicket + ", Fecha: " + fecha + ", Productos: " + productos;
    }
}