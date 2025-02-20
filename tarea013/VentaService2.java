package tarea013;

import java.text.SimpleDateFormat;
import java.util.*;

public class VentaService2 {
    private Inventario2 inventario;
    private List<Venta2> ventas;

    public VentaService2(Inventario2 inventario) {
        this.inventario = inventario;
        this.ventas = new ArrayList<>();
    }

    public void realizarVenta(List<Producto2> productosVendidos) {
        for (Producto2 producto : productosVendidos) {
            inventario.reducirStock(producto.getCodigo(), 1); 
        }

        String fechaActual = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());

        Venta2 venta = new Venta2(generarIdTicket(), fechaActual, productosVendidos);
        ventas.add(venta);
    }

    public List<Venta2> obtenerVentas() {
        return ventas;
    }

    private String generarIdTicket() {
        return "TICKET-" + (ventas.size() + 1);
    }
}