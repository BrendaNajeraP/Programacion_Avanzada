package Aproyecto;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private final List<Producto> productos;

    public Inventario() {
        productos = new ArrayList<>();
    }

    public synchronized void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public synchronized void reducirStock(String id, int cantidad) {
        for (Producto producto : productos) {
            if (producto.getId().equals(id)) {
                if (producto.getCantidad() >= cantidad) {
                    producto.setCantidad(producto.getCantidad() - cantidad);
                } else {
                    System.out.println("No hay suficiente stock para el producto: " + producto.getNombre());
                }
                return;
            }
        }
        System.out.println("Producto no encontrado: " + id);
    }

    public List<Producto> getProductos() {
        return productos;
    }
}