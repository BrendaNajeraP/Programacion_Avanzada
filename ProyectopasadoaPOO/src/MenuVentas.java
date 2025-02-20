package ProyectopasadoaPOO;

import ProyectopasadoaPOO.*;
import java.util.*;

public class MenuVentas {
    private VentaService ventaService;
    private ProductoService productoService; // Agregar ProductoService
    private Scanner scanner;

    public MenuVentas(VentaService ventaService, ProductoService productoService) {
        this.ventaService = ventaService;
        this.productoService = productoService; // Inicializar ProductoService
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Menú de Ventas ---");
            System.out.println("1. Realizar Venta");
            System.out.println("2. Listar Ventas");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    realizarVenta();
                    break;
                case 2:
                    listarVentas();
                    break;
                case 3:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
    }

    private void realizarVenta() {
        List<Producto> productosVendidos = new ArrayList<>();
        String codigo;
        do {
            System.out.print("Ingrese el código del producto (o 'fin' para terminar): ");
            codigo = scanner.nextLine();
            if (!codigo.equalsIgnoreCase("fin")) {
                Producto producto = productoService.buscarProductoPorCodigo(codigo); // Usar ProductoService
                if (producto != null) {
                    productosVendidos.add(producto);
                    System.out.println("Producto agregado a la venta: " + producto.getNombre());
                } else {
                    System.out.println("Producto no encontrado.");
                }
            }
        } while (!codigo.equalsIgnoreCase("fin"));

        if (!productosVendidos.isEmpty()) {
            ventaService.realizarVenta(productosVendidos);
            System.out.println("Venta realizada correctamente.");
        } else {
            System.out.println("No se agregaron productos a la venta.");
        }
    }

    private void listarVentas() {
        System.out.println("\n--- Lista de Ventas ---");
        for (Venta venta : ventaService.obtenerVentas()) {
            System.out.println(venta);
        }
    }
}