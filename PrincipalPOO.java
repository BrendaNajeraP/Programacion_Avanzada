package Aproyecto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrincipalPOO {
   public static void main(String[] args) throws IOException {
       Inventario inventario = new Inventario();
       InterfazUsuario interfaz = new InterfazUsuario();
       PersistenciaDatos<Producto> persistencia = new PersistenciaDatos<>();

       while (true) {
           interfaz.mostrarMenu();
           int opcion = interfaz.leerOpcion();

           switch (opcion) {
               case 1:
                   Producto producto = interfaz.leerProducto();
                   inventario.agregarProducto(producto);
                   break;
               case 2:
                   Venta venta = interfaz.leerVenta();
                   inventario.reducirStock(venta.getId(), venta.getCantidadVendida());
                   break;
               case 3:
                   for (Producto p : inventario.getProductos()) {
                       System.out.println(p);
                   }
                   break;
               case 4:
                   persistencia.guardarCSV(inventario.getProductos(), "inventario.csv");
                   persistencia.guardarJSON(inventario.getProductos(), "inventario.json");
                   System.out.println("Saliendo del sistema...");
                   return;
               default:
                   System.out.println("Opción no válida.");
           }
       }
   }
}