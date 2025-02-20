package ProyectopasadoaPOO;

import ProyectopasadoaPOO.*;
import java.util.*;

public class MenuProductos {
    private ProductoService productoService;
    private Scanner scanner;

    public MenuProductos(ProductoService productoService) {
        this.productoService = productoService;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Menú de Productos ---");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Listar Productos");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    listarProductos();
                    break;
                case 3:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
    }

    private void agregarProducto() {
        System.out.print("Ingrese el código del producto: ");
        String codigo = scanner.nextLine();
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el precio del producto: ");
        double precio = scanner.nextDouble();
        System.out.print("Ingrese la cantidad en stock: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Producto producto = new Producto(codigo, nombre, precio, cantidad);
        productoService.agregarProducto(producto);
        System.out.println("Producto agregado correctamente.");
    }

    private void listarProductos() {
        System.out.println("\n--- Lista de Productos ---");
        for (Producto producto : productoService.obtenerProductos()) {
            System.out.println(producto);
        }
    }
}