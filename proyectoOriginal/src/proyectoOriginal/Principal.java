package proyectoOriginal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Principal {

    static String[][] _products_;
    static String _ventas_[][];
    static int _tamventas = 100;
    static String _fecha_;

    public static String MostrarMenu(String[] opciones) {
        String cadena = "";
        for (String info : opciones) {
            cadena = cadena + info + "\n";
        }
        return cadena;
    }

    public static boolean EshumeroEntero(String dato) {
        for (char c : dato.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean EshumeroDouble(String dato) {
        boolean valido = false;
        for (char c : dato.toCharArray()) {
            if (!Character.isDigit(c)) {
                if (c == '.' && !valido) {
                    valido = true;
                } else {
                    return false;
                }
            }
        }
        return valido;
    }

    public static boolean EvaluarNumerico(String dato, int tipo) {
        boolean valido = false;
        switch (tipo) {
            case 1:
                valido = EshumeroEntero(dato);
                break;
            case 2:
                valido = EshumeroDouble(dato);
                break;
        }
        return valido;
    }

    public static String Dialogo(String texto) throws IOException {
        String cadena;
        System.out.println(texto + " : ");
        BufferedReader lectura = new BufferedReader(new InputStreamReader(System.in));
        cadena = lectura.readLine();
        return cadena;
    }

    public static String Leer(String texto) throws IOException {
        String cadena = "";
        cadena = Dialogo(texto);
        if (cadena != null) {
            cadena = cadena.trim();
            if (cadena.isEmpty())
                cadena = null;
        } else
            cadena = null;
        return cadena;
    }

    public static String DesplegarMenu(String Titulol, String[] menu) throws IOException {
        String cadena;
        cadena = Titulol + "\n" + "\n";
        cadena = cadena + MostrarMenu(menu);
        cadena = cadena + "\n Que opcion deseas ";
        return cadena = Dialogo(cadena);
    }

    public static String RellenarEspacios(String dato, int tamano) {
        return String.format("%1$-" + tamano + "s", dato);
    }

    public static String Fecha() {
        Date fecha = new Date();
        SimpleDateFormat formatodia = new SimpleDateFormat("dd-MM-yyyy");
        return formatodia.format(fecha);
    }

    public static String IdTicketSiguiente(String idticket) {
        String idticketnext = "";
        int num = Integer.parseInt(idticket) + 1;
        if (num < 10) {
            idticketnext = "00" + String.valueOf(num).trim();
        } else if ((num > 9) && (num < 100)) {
            idticketnext = "0" + String.valueOf(num).trim();
        } else {
            idticketnext = String.valueOf(num).trim();
        }
        return idticketnext;
    }

    public static int ObteneuUltimaPosition(String[][] matriz) {
        int ultimaPosition = -1;
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][0] != null && !matriz[i][0].isEmpty()) {
                ultimaPosition = i;
            }
        }
        return ultimaPosition;
    }

    public static String[][] CargarProductos() {
        String[][] producto = {
                {"001", "Arroz 1kg", "35", "10"},
                {"002", "Azúcar 1kg", "25", "10"},
                {"003", "Harina 1kg", "28", "10"},
                {"004", "Aceite 11", "50", "10"},
                {"005", "Leche 11", "35", "10"},
                {"006", "Huevos 12 unidades", "45", "10"},
                {"007", "Fideos 500g", "20", "10"},
                {"008", "Sal 1kg", "15", "10"},
                {"009", "Pasta de tomate 400g", "25", "10"},
                {"010", "Atún lata 170g", "35", "10"}
        };
        return producto;
    }

    public static String MostrarProducto(String[] vproducto) {
        String codigo = RellenarEspacios(vproducto[0], 5);
        String producto = RellenarEspacios(vproducto[1], 30);
        String precio = RellenarEspacios(vproducto[2], 10);
        String cantidad = RellenarEspacios(vproducto[3], 10);
        String cadena = codigo.concat(producto + precio + cantidad);
        return cadena;
    }

    public static String MostrarLista(String[][] vproductos) {
        String salida = "";
        for (int ciclo = 0; ciclo < 10; ciclo++) {
            String[] vproducto = {vproductos[ciclo][0], vproductos[ciclo][1], vproductos[ciclo][2], vproductos[ciclo][3]};
            String cadena = MostrarProducto(vproducto);
            salida = salida.concat(cadena + "\n");
        }
        return salida;
    }

    public static int Existeproducto(String codigo, String[][] vproductos) {
        int enc = -1;
        int pos = 0;
        int tam = vproductos.length;
        for (int ciclo = 0; ciclo < tam; ciclo++) {
            if (vproductos[ciclo][0].compareTo(codigo.trim()) == 0) {
                enc = pos;
                pos++;
            }
        }
        return enc;
    }

    public static void ModificarProducto(String[][] vproductos) throws IOException {
        String codigo, precio;
        int posicion;
        String info = MostrarLista(vproductos);
        codigo = Leer(info + "\nIntroduce el codigo del producto a modificar");
        if (codigo != null) {
            posicion = Existeproducto(codigo, vproductos);
            if (posicion > -1) {
                String[] vproducto = {vproductos[posicion][0], vproductos[posicion][1], vproductos[posicion][2], vproductos[posicion][3]};
                precio = Leer("\nIntroduce el precio de " + MostrarProducto(vproducto) + " ");
                if (precio != null) {
                    if (EvaluarNumerico(precio, 2) || EvaluarNumerico(precio, 1))
                        vproductos[posicion][2] = precio;
                    else
                        System.out.println("no es un valor numerico");
                } else
                    System.out.println(" dato nulo");
            } else
                System.out.println("no existe el codigo");
        } else
            System.out.println(" dato nulo");
    }

    public static void MenuProductos(String[][] vproductos) throws IOException {
        String[] datosmenuproductos = {"1.-Modificar ", "2.-Listado ", "3.-Salida "};
        String opcion = "0";
        do {
            opcion = DesplegarMenu("Opciones de Productos", datosmenuproductos);
            if (opcion == null)
                System.out.println("opcion incorrecta ");
            else
                switch (opcion) {
                    case "1":
                        ModificarProducto(vproductos);
                        break;
                    case "2":
                        System.out.println(MostrarLista(vproductos));
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

    public static String[][] CrearVenta() {
        return new String[_tamventas][5];
    }

    public static String UltimoTicket(int pos, String[][] mventa) {
        String idticket = "000";
        if (pos > -1) {
            idticket = mventa[pos][0];
        }
        return idticket;
    }

    public static String[][] CreamTicket() {
        return new String[20][4];
    }

    public static int ExisteTicketCodigo(String[][] mitcket, String codigo) {
        int enc = -1;
        int pos = ObteneuUltimaPosition(mitcket);
        System.out.println(" buscando " + codigo + " tamaño arreglo " + pos);
        for (int ciclo = 0; ciclo <= pos; ciclo++) {
            if (mitcket[ciclo][0].compareTo(codigo.trim()) == 0) {
                enc = ciclo;
                return enc;
            }
        }
        return enc;
    }

    public static boolean InsertanProductOficket(String[][] mitcket, String[] datos, int tamticket) {
        boolean succdio = true;
        int posticket = ObteneuUltimaPosition(mitcket);
        int enc = ExisteTicketCodigo(mitcket, datos[0]);
        if (posticket < tamticket) {
            if (enc > -1) {
                System.out.println("ya existe el producto en el ticket lo incrementare");
                int cantidadactual = Integer.parseInt(mitcket[enc][3]);
                mitcket[enc][3] = String.valueOf(cantidadactual + 1);
            } else {
                posticket++;
                mitcket[posticket][0] = datos[0];
                mitcket[posticket][1] = datos[1];
                mitcket[posticket][2] = datos[2];
                mitcket[posticket][3] = datos[3];
                System.out.println("Insertamdo No existe el producto en el ticket ");
            }
        } else
            succdio = false;
        return succdio;
    }

    public static String TotalProducto(String precio, String cantidad) {
        double total = Double.parseDouble(precio) * Double.parseDouble(cantidad);
        return String.format("%.2f", total);
    }

    public static String MostrarProductOricket(String[][] mricket, int pos) {
        String codigo = RellenarEspacios(mricket[pos][0], 5);
        String producto = RellenarEspacios(mricket[pos][1], 30);
        String precio = RellenarEspacios(mricket[pos][2], 10);
        String cantidad = RellenarEspacios(mricket[pos][3], 5);
        String totalproducto = RellenarEspacios(TotalProducto(mricket[pos][2], mricket[pos][3]), 10);
        String cadena = codigo.concat(producto + precio + cantidad + totalproducto);
        return cadena;
    }

    public static String MostrarTicket(String[][] mricket) {
        String salida = "";
        int pos = ObteneuUltimaPosition(mricket);
        for (int ciclo = 0; ciclo <= pos; ciclo++) {
            salida = salida.concat(MostrarProductOricket(mricket, ciclo) + "\n");
        }
        return salida;
    }

    public static String MostrarTicketVenta(String[][] mricket, String idticket, String fecha) {
        String salida = "";
        String subtotal = String.format("%.2f", SubTotalTicket(mricket));
        String iva = String.format("%.2f", IvaTicket(mricket));
        String total = String.format("%.2f", TotalTicket(mricket));
        salida = "Fecha " + fecha + "Ticket No." + idticket;
        salida = salida + "\n" + MostrarTicket(mricket);
        salida = salida + "\n \n El total sin iva " + subtotal;
        salida = salida + "\n el iva total es " + iva;
        salida = salida + "\n el total de la venta fue " + total;
        return salida;
    }

    public static String MostrarListaProductosVenta(String[][] vproductos) {
        String salida = "";
        for (int ciclo = 0; ciclo < 10; ciclo++) {
            int existencia = Integer.parseInt(vproductos[ciclo][3]);
            if (existencia > 0) {
                String[] vproducto = vproductos[ciclo].clone();
                String cadena = MostrarProducto(vproducto);
                salida = salida.concat(cadena + "\n");
            }
        }
        return salida;
    }

    public static void CapturaVentaProducto(String[][] miticket, String[][] mproductos, String idticket, String codigo, String info) throws IOException {
        info = MostrarListaProductosVenta(mproductos);
        codigo = Leer(info + "\nIntroduce el codigo del producto");
        if (codigo != null) {
            int posp = Existeproducto(codigo.trim(), mproductos);
            if (posp > -1) {
                if (Integer.parseInt(mproductos[posp][3]) > 0) {
                    String[] producto = mproductos[posp].clone();
                    System.out.println(MostrarProducto(producto));
                    String[] venta = new String[4];
                    venta[0] = producto[posp][0];
                    venta[1] = producto[posp][1];
                    venta[2] = producto[posp][2];
                    venta[3] = "1";
                    if (!InsertanProductOficket(miticket, venta, 50))
                        System.out.println("el Arreglo esta lleno \n");
                } else
                    System.out.println("no hay productos para venta");
            } else
                System.out.println("el codigo no existe no se puede agregar\n ");
        } else
            System.out.println("dato nulo\n ");
    }

    public static void RemoveProductoTicket(String[][] miticket, int pos) {
        int tam = ObteneuUltimaPosition(miticket);
        if (tam == pos) {
            for (int i = pos; i < tam + 1; i++) {
                miticket[i] = miticket[i + 1];
            }
            miticket[tam][0] = null;
        } else
            miticket[pos][0] = null;
    }

    public static void EliminarProductoricket(String[][] mitcket, int pos) {
        int cantidad = Integer.parseInt(mitcket[pos][3]);
        if (cantidad > 1)
            mitcket[pos][3] = String.valueOf(cantidad - 1);
        else
            RemoveProductoTicket(mitcket, pos);
    }

    public static void Eliminar(String[][] mitcket, int tamt) throws IOException {
        String codigo, info;
        info = MostrarTicket(mitcket);
        codigo = Leer(info + "\nIntroduce el codigo del producto");
        if (codigo != null) {
            int pos = ExisteTicketCodigo(mitcket, codigo);
            if (pos > -1)
                EliminarProductoricket(mitcket, pos);
        } else
            System.out.println("dato nulo");
    }

    public static void AgregarProductoaVenta(String[][] mitcket, String[][] mventa, String idticket) {
        int posventas = ObteneuUltimaPosition(mventa);
        int posticket = ObteneuUltimaPosition(mitcket);
        for (int i = 0; i <= posticket; i++) {
            if (mitcket[i][0] != null) {
                posventas++;
                mventa[posventas][0] = idticket;
                mventa[posventas][1] = mitcket[i][0];
                mventa[posventas][2] = mitcket[i][1];
                mventa[posventas][3] = mitcket[i][2];
                mventa[posventas][4] = mitcket[i][3];
            }
        }
    }

    public static void Pagar(String idticket, String[][] mventa, String[][] mitcket) {
        int posventas = ObteneuUltimaPosition(mventa);
        int post = ObteneuUltimaPosition(mitcket);
        if ((posventas + post) < 100) {
            AgregarProductoaVenta(mitcket, mventa, idticket);
        } else {
            System.out.println("Desbordamiento de Memoria de ventas");
        }
    }

    public static void MenuPuntoVenta(String[][] ventas, String idticket, String[][] productos) throws IOException {
        String option, membrette;
        Boolean pago = false;
        int tamticket = 50;
        String[][] Viticket = new String[tamticket][4];
        idticket = IdTicketSiguiente(idticket);
        String fechadia = Fecha();
        option = "";
        do {
            membrette = "Fecha del Dia " + fechadia + " Ticket No " + idticket;
            membrette = membrette + "\n---\n";
            String Tickettexto = MostrarTicket(Viticket).trim();
            if (!Tickettexto.trim().isEmpty()) {
                membrette = membrette + "\n" + Tickettexto + "\n";
            }
            String[] datosmenu = {"1.-Agregar ", "2.-Eliminar ", "3.-Listado ", "4.-Pagar ", "5.-Salida "};
            option = DesplegarMenu(membrette + "\n Menu de Punto de Venta", datosmenu);
            if (option == null)
                System.out.println("dato incorrecto introducido");
            else
                switch (option) {
                    case "1":
                        CapturaVentaProducto(Viticket, productos, idticket, tamticket);
                        break;
                    case "2":
                        Eliminar(Viticket, tamticket);
                        break;
                    case "3":if (ObtenerUltimaPosicion(Vticket) > -1)
                        System.out.println(MostrarTicket(Vticket));
                    break; 
                case "4": 
                    System.out.println(MostrarTicketVenta(Vticket, idticket, fechadia).trim());
                    Pagar(idticket, ventas, Vticket);
                    pago = true;
                    opcion = "5"; 
                    break;
                case "5": 
                    System.out.println("Salida del Ventas "); 
                    if (!pago) { 
                        System.out.println("No pago el ticket ");
                        System.out.println("eliminando ticket " + idticket);
                    }
                    break; 
                default: 
                    System.out.println("No existe esta opción"); 
                    break; 
            }  
        }
    } while (!opcion.equals("5"));
}

public static void MenuInventario(String[][] vproductos) throws IOException { 
    String[] datosmenuinventario = { "1.-Listado ", "2.-Agregar ", "3.-Salida " }; 
    String opcion = "0"; 

    do {
        opcion = DesplegarMenu("Opciones de Inventarios", datosmenuinventario); 
        if (opcion == null) {
            System.out.println("opción incorrecta ");
        } else {
            switch (opcion) { 
                case "1": System.out.println(MostrarLista(vproductos)); break; 
                case "2": AgregarStock(vproductos); break; 
                case "3": System.out.println("Salida del Sistema "); break; 
                default: System.out.println("No existe esta opción "); break; 
            }
        }
    } while (!opcion.equals("3")); 
}

public static void MenuPrincipal(String[][] vproductos, String[][] vventas) throws IOException {  
    String[] datosmenuprincipal = { "1.-Productos ", "2.-Punto de Venta ", "3.- Inventario", "4.-Ventas", "5.-Salir" };
    String opcion;
    String idticket;

    do {
        idticket = ObtenerUltimoValorVentas(vventas);
        opcion = DesplegarMenu("Menu de Punto de Tienda de Abarrotes la Pequeña", datosmenuprincipal); 
        
        if (opcion == null) {
            System.out.println("Opción incorrecta");
        } else {
            switch (opcion) {
                case "1": MenuProductos(vproductos); break; 
                case "2": MenuPuntoVenta(vventas, idticket, vproductos); break; 
                case "3": MenuInventario(vproductos); break;
                case "4": System.out.println(MostrarLista(vventas)); break;
                case "5": System.out.println("Salida del Sistema"); break;
                default: System.out.println("No existe esta opción"); break;
            }
        }
    } while (!opcion.equals("5"));
}

public static String ObtenerUltimoValorVentas(String[][] ventas) {
    String ultimoValor = "000";
    for (int i = ventas.length - 1; i >= 0; i--) {
        if (ventas[i][0] != null && !ventas[i][0].isEmpty()) {
            ultimoValor = ventas[i][0];
            break;
        }
    }
    return ultimoValor;
}

public static void main(String[] args) throws IOException {
    productos = CargarProductos();
    ventas = CrearVenta(); 
    MenuPrincipal(productos, ventas);
}
}