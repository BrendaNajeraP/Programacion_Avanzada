package ProyectopasadoaPOO;

import java.util.List;

public class Venta {
    private String idTicket;
    private String fecha;
    private List<Producto> productos;

    public Venta(String idTicket, String fecha, List<Producto> productos) {
        this.idTicket = idTicket;
        this.fecha = fecha;
        this.productos = productos;
    }

    // Getters y Setters
    public String getIdTicket() { return idTicket; }
    public String getFecha() { return fecha; }
    public List<Producto> getProductos() { return productos; }

    @Override
    public String toString() {
        return "Ticket: " + idTicket + ", Fecha: " + fecha + ", Productos: " + productos;
    }
}