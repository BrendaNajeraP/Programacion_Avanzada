package ProyectopasadoaPOO;

import ProyectopasadoaPOO.*;
import java.util.List;

public class ProductoService {
    private Inventario inventario;

    public ProductoService(Inventario inventario) {
        this.inventario = inventario;
    }

    public void agregarProducto(Producto producto) {
        inventario.agregarProducto(producto);
    }

    public void reducirStock(String codigo, int cantidad) {
        inventario.reducirStock(codigo, cantidad);
    }

    public List<Producto> obtenerProductos() {
        return inventario.getProductos();
    }

    public Producto buscarProductoPorCodigo(String codigo) {
        for (Producto producto : inventario.getProductos()) {
            if (producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }
        return null;
    }
}