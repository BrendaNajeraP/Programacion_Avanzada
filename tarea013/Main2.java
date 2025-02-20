package tarea013;



public class Main2 {
    public static void main(String[] args) {
    	
    	
        Inventario2 inventario = new Inventario2();
        ProductoService2 productoService = new ProductoService2(inventario);
        VentaService2 ventaService = new VentaService2(inventario);

        MenuPrincipal2 menuPrincipal = new MenuPrincipal2(productoService, ventaService);
        menuPrincipal.mostrarMenu();
        
        
    }
}