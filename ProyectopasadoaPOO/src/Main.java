package ProyectopasadoaPOO;

import ProyectopasadoaPOO.*;

public class Main {
    public static void main(String[] args) {
        // Crear las dependencias necesarias
        Inventario inventario = new Inventario();
        ProductoService productoService = new ProductoService(inventario);
        VentaService ventaService = new VentaService(inventario);

        // Crear y mostrar el menú principal
        MenuPrincipal menuPrincipal = new MenuPrincipal(productoService, ventaService);
        menuPrincipal.mostrarMenu();
    }
}