package ProyectopasadoaPOO;

import ProyectopasadoaPOO.*;
import java.util.*;
public class VentaService {
    private Inventario inventario;
    private List<Venta> ventas;

    public VentaService(Inventario inventario) {
        this.inventario = inventario;
        this.ventas = new ArrayList<>();
    }

    public void realizarVenta(List<Producto> productosVendidos) {
        for (Producto producto : productosVendidos) {
            inventario.reducirStock(producto.getCodigo(), 1); // Reducir stock en 1 unidad
        }
        Venta venta = new Venta(generarIdTicket(), "FechaActual", productosVendidos);
        ventas.add(venta);
    }

    public List<Venta> obtenerVentas() {
        return ventas;
    }

    private String generarIdTicket() {
        return "TICKET-" + (ventas.size() + 1);
    }
}