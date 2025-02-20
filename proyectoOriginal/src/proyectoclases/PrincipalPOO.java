package proyectoclases;

import java.io.IOException;
import java.util.ArrayList;

public class PrincipalPOO {
    static Lista<Cproducto> productos;
    static String ventas[][];
    static int tamventas = 100;
    static String fecha;

    public static Lista<Cproducto> CargarProductos() {
        Lista<Cproducto> producto = new Lista<>();
        producto.insertar(new Cproducto("001", "Arroz 1kg", "35", "10"));
        producto.insertar(new Cproducto("002", "Azúcar 1kg", "25", "10"));
        producto.insertar(new Cproducto("003", "Harina 1kg", "28", "10"));
        producto.insertar(new Cproducto("005", "Leche 1L", "35", "10"));
        producto.insertar(new Cproducto("006", "Huevos 12 unidades", "45", "10"));
        producto.insertar(new Cproducto("007", "Fideos 500g", "20", "10"));
        producto.insertar(new Cproducto("008", "Sal 1kg", "15", "10"));
        producto.insertar(new Cproducto("009", "Pasta de tomate 400g", "25", "10"));
        producto.insertar(new Cproducto("010", "Atún lata 170g", "35", "10"));
        return producto;
    }

    public static void MenuProductos(Lista<Cproducto> vproductos) throws IOException {
        ArrayList<String> datosmenuproductos = new ArrayList<>();
        datosmenuproductos.add("1.-Modificar ");
        datosmenuproductos.add("2.-Listado ");
        datosmenuproductos.add("3.-Salida ");
        String opcion = "0";
        do {
            opcion = Libreria.DesplegarMenu("Opciones de Productos", datosmenuproductos);
            if (opcion == null)
                System.out.println("opcion incorrecta ");
            else
                switch (opcion) {
                    case "1":
                        System.out.println("Modificar");
                        break;
                    case "2":
                        System.out.println(vproductos.MostrarLista());
                        break;
                    case "3":
                        System.out.println("Salida del Sistema ");
                        break;
                    default:
                        System.out.println("No existe esta opcion ");
                        break;
                }
        } while (opcion.compareTo("3") != 0);
    }

    public static void MenuPrincipal(Lista<Cproducto> vproductos) throws IOException {
        ArrayList<String> datosmenuprincipal = new ArrayList<>();
        datosmenuprincipal.add("1.-Productos ");
        datosmenuprincipal.add("2.-Punto de Venta ");
        datosmenuprincipal.add("3.- Inventario");
        datosmenuprincipal.add("4.-Ventas");
        datosmenuprincipal.add("5.-Salida ");
        String opcion = "0";
        do {
            opcion = Libreria.DesplegarMenu("Menu de Punto de Tienda de Abarrotes la Pequeñina", datosmenuprincipal);
            if (opcion == null)
                System.out.println("opcion incorrecta ");
            else
                switch (opcion) {
                    case "1":
                        MenuProductos(vproductos);
                        break;
                    case "2":
                        System.out.println("PUNTO DE VENTAS ");
                        break;
                    case "3":
                        System.out.println("IVENTARIOS");
                        break;
                    case "4":
                        System.out.println("VENTAS");
                        break;
                    case "5":
                        System.out.println("Salida del Sistema ");
                        break;
                    default:
                        System.out.println("No existe esta opcion ");
                        break;
                }
        } while (opcion.compareTo("5") != 0);
    }

    public static void main(String[] args) throws IOException {
        try {
            productos = CargarProductos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MenuPrincipal(productos);
    }
}