package ProyectopasadoaPOO;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipal {
    private ProductoService productoService;
    private VentaService ventaService;
    private Scanner scanner;

    // Constantes para las opciones del menú
    private static final int OPCION_GESTION_PRODUCTOS = 1;
    private static final int OPCION_GESTION_VENTAS = 2;
    private static final int OPCION_SALIR = 3;

    public MenuPrincipal(ProductoService productoService, VentaService ventaService) {
        this.productoService = productoService;
        this.ventaService = ventaService;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            mostrarOpciones();
            opcion = leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != OPCION_SALIR);

        cerrarScanner();
    }

    private void mostrarOpciones() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println(OPCION_GESTION_PRODUCTOS + ". Gestión de Productos");
        System.out.println(OPCION_GESTION_VENTAS + ". Gestión de Ventas");
        System.out.println(OPCION_SALIR + ". Salir");
        System.out.print("Seleccione una opción: ");
    }

    private int leerOpcion() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número válido.");
            scanner.next(); // Limpiar el buffer del scanner
            return -1; // Retorna un valor inválido para repetir el ciclo
        }
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case OPCION_GESTION_PRODUCTOS:
                new MenuProductos(productoService).mostrarMenu();
                break;
            case OPCION_GESTION_VENTAS:
                new MenuVentas(ventaService, productoService).mostrarMenu(); // Pasar productoService
                break;
            case OPCION_SALIR:
                System.out.println("Saliendo del sistema...");
                break;
            default:
                System.out.println("Opción no válida. Intente nuevamente.");
        }
    }

    private void cerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}