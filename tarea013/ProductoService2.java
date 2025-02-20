package tarea013;

import java.util.List;

public class ProductoService2 {
    private Inventario2 inventario;

    public ProductoService2(Inventario2 inventario) {
        this.inventario = inventario;
    }

    public void agregarProducto(Producto2 producto) {
        inventario.agregarProducto(producto);
    }

    public void reducirStock(String codigo, int cantidad) {
        inventario.reducirStock(codigo, cantidad);
    }

    public List<Producto2> obtenerProductos() {
        return inventario.getProductos();
    }

    public Producto2 buscarProductoPorCodigo(String codigo) {
        for (Producto2 producto : inventario.getProductos()) {
            if (producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }
        return null;
    }
}