package tarea013;

import java.util.ArrayList;
import java.util.List;

public class Inventario2 {
    private List<Producto2> productos;

    public Inventario2() {
        productos = new ArrayList<>();
    }

   
    public void agregarProducto(Producto2 producto) {
        productos.add(producto);
    }

    public void reducirStock(String codigo, int cantidad) {
        for (Producto2 producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                producto.setCantidad(producto.getCantidad() - cantidad);
                break;
            }
        }
    }

    public List<Producto2> getProductos() {
        return productos;
    }
}