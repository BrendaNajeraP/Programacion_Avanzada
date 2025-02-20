package tarea013;

import java.util.*;

public class MenuVentas2 {
    private VentaService2 ventaService;
    private ProductoService2 productoService; 
    private Scanner scanner;

    public MenuVentas2(VentaService2 ventaService, ProductoService2 productoService) {
        this.ventaService = ventaService;
        this.productoService = productoService;
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
            scanner.nextLine(); 

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
        List<Producto2> productosVendidos = new ArrayList<>();
        String codigo;
        do {
            System.out.print("Ingrese el código del producto (o 'fin' para terminar): ");
            codigo = scanner.nextLine();
            if (!codigo.equalsIgnoreCase("fin")) {
                Producto2 producto = productoService.buscarProductoPorCodigo(codigo); 
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
        for (Venta2 venta : ventaService.obtenerVentas()) {
            System.out.println(venta);
        }
    }
}