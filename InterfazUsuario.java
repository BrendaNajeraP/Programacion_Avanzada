package Aproyecto;

import java.util.Scanner;

public class InterfazUsuario {
    private Scanner scanner;

    public InterfazUsuario() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("1. Agregar Producto");
        System.out.println("2. Realizar Venta");
        System.out.println("3. Mostrar Inventario");
        System.out.println("4. Salir");
    }

    public int leerOpcion() {
        System.out.print("Seleccione una opción: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Ingrese un número válido.");
            scanner.next();
        }
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return opcion;
    }

    public Producto leerProducto() {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Ingrese un precio válido.");
            scanner.next();
        }
        double precio = scanner.nextDouble();
        System.out.print("Cantidad: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Ingrese una cantidad válida.");
            scanner.next();
        }
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return new Producto(id, nombre, precio, cantidad);
    }

    public Venta leerVenta() {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Ingrese un precio válido.");
            scanner.next();
        }
        double precio = scanner.nextDouble();
        System.out.print("Cantidad Vendida: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Ingrese una cantidad válida.");
            scanner.next();
        }
        int cantidadVendida = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return new Venta(id, nombre, precio, cantidadVendida);
    }

    public void cerrarScanner() {
        scanner.close();
    }
}